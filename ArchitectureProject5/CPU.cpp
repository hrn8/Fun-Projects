/******************************
 * Name:  Hunter Noey hrn8
 * CS 3339 - Spring 2019
 ******************************/
#include "CPU.h"
#include <iomanip>

const string CPU::regNames[] = {"$zero","$at","$v0","$v1","$a0","$a1","$a2","$a3",
                                "$t0","$t1","$t2","$t3","$t4","$t5","$t6","$t7",
                                "$s0","$s1","$s2","$s3","$s4","$s5","$s6","$s7",
                                "$t8","$t9","$k0","$k1","$gp","$sp","$fp","$ra"};

CPU::CPU(uint32_t pc, Memory &iMem, Memory &dMem) : pc(pc), iMem(iMem), dMem(dMem) {
  for(int i = 0; i < NREGS; i++) {
    regFile[i] = 0;
  }
  hi = 0;
  lo = 0;
  regFile[28] = 0x10008000; // gp
  regFile[29] = 0x10000000 + dMem.getSize(); // sp

  instructions = 0;
  stop = false;
}

void CPU::run() {
  while(!stop) {
    instructions++;

    fetch();
    decode();
    execute();
    mem();
    writeback();
    stats.clock(); 
    D(printRegFile());
  }
}

void CPU::fetch() {
  instr = iMem.loadWord(pc);
  pc = pc + 4;
}

/////////////////////////////////////////
// ALL YOUR CHANGES GO IN THIS FUNCTION 
/////////////////////////////////////////
void CPU::decode() {
  uint32_t opcode;      // opcode field
  uint32_t rs, rt, rd;  // register specifiers
  uint32_t shamt;       // shift amount (R-type)
  uint32_t funct;       // funct field (R-type)
  uint32_t uimm;        // unsigned version of immediate (I-type)
  int32_t simm;         // signed version of immediate (I-type)
  uint32_t addr;        // jump address offset field (J-type)
  
  opcode = (instr >> 26);   //opcode is last 6 bits of instruction
  rs = (instr >> 21) & 0x1F; //rs is set to equal 5 bits (21-25 bits of instruction)
  rt = (instr >> 16) & 0x1F; //rt is set to equal 5 bits (16-20 bits of instruction)
  rd = (instr >> 11) & 0x1F; //rd is set to equal 5 bits (11-15 bits of instruction)
  shamt = (instr >> 6) & 0x1F; //rt is set to equal 5 bits (6-10 bits of instruction)
  funct = (instr & 0x3F); //funct is set to equal first 6 bits of instr (bits 26-31)
  uimm = (instr & 0xFFFF); //uimm is set to equal last 16 bits of instr (bits 0-15)
  simm = ((signed) instr << 16) >> 16; //Set simm to equal uimm before bit-shifting
  addr = (instr & 0x3FFFFFF); //addr equals 26 bits of instr (0-25)

  // Hint: you probably want to give all the control signals some "safe"
  // default value here, and then override their values as necessary in each
  // case statement below!
  //Safe default values 
  
  opIsLoad = false;
  opIsStore = false; 
  opIsMultDiv = false;
  writeDest = false;
  
 
  D(cout << "  " << hex << setw(8) << pc - 4 << ": ");
  switch(opcode) {
    case 0x00:
      switch(funct) {
        case 0x00: D(cout << "sll " << regNames[rd] << ", " << regNames[rs] << ", " << dec << shamt);
                   writeDest = true;   destReg = rd;    stats.registerDest(rd);   //Sends the sll outcome to regFile[rd]
                   aluOp = SHF_L;                       //shifts regFile[rs] left by shamt into regFile[rd]
                   aluSrc1 = regFile[rs];               stats.registerSrc(rs);    
                   aluSrc2 = shamt;                     
                   break;
        case 0x03: D(cout << "sra " << regNames[rd] << ", " << regNames[rs] << ", " << dec << shamt);
                   writeDest = true;   destReg = rd;    stats.registerDest(rd);   //Sends the sll outcome to regFile[rd]
                   aluOp = SHF_R;                        //shifts regFile[rs] right by shamt into regFile[rd]
                   aluSrc1 = regFile[rs];               stats.registerSrc(rs);
                   aluSrc2 = shamt; 
                   break;
        case 0x08: D(cout << "jr " << regNames[rs]);
                   writeDest = true; destReg = rs;     stats.registerDest(rs);//sets destination to regFile[rs]
                   stats.flush(2);
                   aluOp = ADD; // ALU should pass pc thru unchanged
                   aluSrc1 = pc;
                   aluSrc2 = regFile[REG_ZERO];        // always reads zero
                   pc = regFile[rs];                  //pc = pc + regFile[rs]
                   break;
        case 0x10: D(cout << "mfhi " << regNames[rd]);
                   writeDest = true;    destReg = rd;   stats.registerDest(rd); //sends mfhi to regFile[rd]
                   aluOp = ADD;                         //allows user to use add Upper and zero to send to regFile[rd]
                   aluSrc1 = regFile[REG_ZERO];         // always reads zero
                   aluSrc2 = alu.getUpper();            stats.registerSrc(REG_HILO);
                   break;
        case 0x12: D(cout << "mflo " << regNames[rd]);
                   writeDest = true;    destReg = rd;   stats.registerDest(rd); //sends mflo to regFile[rd]
                   aluOp = ADD;                        //allows user to use add Lower and zero to send to regFile[rd]
                   aluSrc1 = regFile[REG_ZERO];        // always reads zero
                   aluSrc2 = alu.getLower();           stats.registerSrc(REG_HILO);
                   break;
        case 0x18: D(cout << "mult " << regNames[rs] << ", " << regNames[rt]);
                   opIsMultDiv = true;                
                   aluOp = MUL;             stats.registerDest(REG_HILO);        //sets the command to multiplication
                   aluSrc1 = regFile[rs];   stats.registerSrc(rs);
                   aluSrc2 = regFile[rt];   stats.registerSrc(rt); 
                   break;
        case 0x1a: D(cout << "div " << regNames[rs] << ", " << regNames[rt]);
                   opIsMultDiv = true;
                   aluOp = DIV;             stats.registerDest(REG_HILO); //sets the command to division
                   aluSrc1 = regFile[rs];   stats.registerSrc(rs);
                   aluSrc2 = regFile[rt];   stats.registerSrc(rt); 
                   break;
        case 0x21: D(cout << "addu " << regNames[rd] << ", " << regNames[rs] << ", " << regNames[rt]);
                   writeDest = true;   destReg = rd;  stats.registerDest(rd);  //sets destination to regFile[rd]
                   aluOp = ADD;                             //sets command to addition
                   aluSrc1 = regFile[rs];   stats.registerSrc(rs);   //first argument is regFile[rs]
                   aluSrc2 = regFile[rt];   stats.registerSrc(rt);   //second argument is regFile[rt]
                   break;
        case 0x23: D(cout << "subu " << regNames[rd] << ", " << regNames[rs] << ", " << regNames[rt]);  
                   writeDest = true;   destReg = rd;    stats.registerDest(rd); //sets destination to regFile[rd]
                   aluOp = ADD;                                     //sets command to addition
                   aluSrc1 = regFile[rs];   stats.registerSrc(rs);  //first argument is regFile[rs]  
                   aluSrc2 = -regFile[rt];   stats.registerSrc(rt); //second argument is regFile[rt]
                  break;
        case 0x2a: D(cout << "slt " << regNames[rd] << ", " << regNames[rs] << ", " << regNames[rt]);
                   writeDest = true;   destReg = rd; stats.registerDest(rd);    //sets destination to regFile[rd]
                   aluOp = CMP_LT;
                   aluSrc1 = regFile[rs];   stats.registerSrc(rs);              //first argument is regFile[rs] 
                   aluSrc2 = regFile[rt];   stats.registerSrc(rt);              //second argument is regFile[rt]
                   break;
        default: cerr << "unimplemented instruction: pc = 0x" << hex << pc - 4 << endl;
      }
      break;
    case 0x02: D(cout << "j " << hex << ((pc & 0xf0000000) | addr << 2)); // P1: pc + 4
               pc = (pc & 0xf0000000) | addr << 2;
               stats.countTaken();                      //counts a taken branch
               stats.countBranch();                     //counts branch
               stats.flush(2);                          //flushes
               break;
    case 0x03: D(cout << "jal " << hex << ((pc & 0xf0000000) | addr << 2)); // P1: pc + 4
               writeDest = true; destReg = REG_RA; stats.registerDest(REG_RA);// writes PC+4 to $ra
               aluOp = ADD; // ALU should pass pc thru unchanged
               aluSrc1 = pc;
               aluSrc2 = regFile[REG_ZERO];     // always reads zero
               pc = (pc & 0xf0000000) | addr << 2;
               stats.countTaken();              //counts a taken branch
               stats.countBranch();             //counts branch
               stats.flush(2);                   //flushes
               break;
    case 0x04: D(cout << "beq " << regNames[rs] << ", " << regNames[rt] << ", " << pc + (simm << 2));
                stats.registerSrc(rs);
                stats.registerSrc(rt);
                if (regFile[rs]== regFile[rt]){
                pc = pc + (simm << 2);  ///if regFile[rs] and regFile[rt] are equal, increment pc
                stats.countTaken();     //counts a taken branch if its equal
                stats.flush(2);         //flushes
                }
               stats.countBranch();     //counts branch regardless of outcome
               break;  // read the handout carefully, update PC directly here as in jal example
    case 0x05: D(cout << "bne " << regNames[rs] << ", " << regNames[rt] << ", " << pc + (simm << 2));
               stats.registerSrc(rs);
               stats.registerSrc(rt);
               if (regFile[rs]!= regFile[rt])   //if regFile[rs] and regFile[rt] are not equal, increment pc
               {
                   pc = pc + (simm << 2);
                   stats.countTaken();           //counts a taken branch if its not equal
                   stats.flush(2);              //flushes
               }    
               stats.countBranch();             //counts branch regardless of outcome
               break;  // same comment as beqm
    case 0x09: D(cout << "addiu " << regNames[rt] << ", " << regNames[rs] << ", " << dec << simm);
               writeDest = true;   destReg = rt;  stats.registerDest(rt);  //sets destination to regFile[rt]
               aluOp = ADD;                         //sets command to addition
               aluSrc1 = regFile[rs];             stats.registerSrc(rs);//first argument is regFile[rs]
               aluSrc2 = simm;                      //second argument is simm
               break;
    case 0x0c: D(cout << "andi " << regNames[rt] << ", " << regNames[rs] << ", " << dec << uimm);
               writeDest = true;   destReg = rt;    stats.registerDest(rt); //sets destination to regFile[rt]
               aluOp = AND;                         //sets command to addition
               aluSrc1 = regFile[rs];               stats.registerSrc(rs); //first argument is regFile[rs]
               aluSrc2 = simm;                      //second argument is simm
               break;
    case 0x0f: D(cout << "lui " << regNames[rt] << ", " << dec << simm);
               writeDest = true;   destReg = rt;   stats.registerDest(rt);
               aluOp = SHF_L;
               aluSrc1 = regFile[rt];              stats.registerSrc(rt);
               aluSrc2 = simm;
               break;
    case 0x1a: D(cout << "trap " << hex << addr);
               switch(addr & 0xf) {
                 case 0x0: cout << endl; break;
                 case 0x1: cout << " " << (signed)regFile[rs];  stats.registerDest(rd);
                           break;
                 case 0x5: cout << endl << "? "; cin >> regFile[rt];    stats.registerSrc(rt);
                           break;
                 case 0xa: stop = true; break;
                 default: cerr << "unimplemented trap: pc = 0x" << hex << pc - 4 << endl;
                          stop = true;
               }
               break;
    case 0x23: D(cout << "lw " << regNames[rt] << ", " << dec << simm << "(" << regNames[rs] << ")");
               opIsLoad = true; writeDest = true; destReg = rt; stats.registerDest(rt); //sets destination to regFile[rt]
               aluOp = ADD;                                     //sets operation to add
               aluSrc1 = regFile[rs];  //Argument 1 is regFile[rs]
              
               
               if(regFile[rs] >> 16 == 0xFFFF )                 //if negative convert to Sign Magnitude
                   aluSrc2 = (simm ^ 0xffffffff)+1;
               else
                   aluSrc2 = simm;                              //If positive keep the value
               
               stats.countMemOp(); 
               regFile[rt] = writeData;
               break;  // do not interact with memory here - setup control signals for mem() below
    case 0x2b: D(cout << "sw " << regNames[rt] << ", " << dec << simm << "(" << regNames[rs] << ")");
               opIsStore = true;                                //store operation
               aluOp = ADD;                                     //sets operation to add
               aluSrc1 = regFile[rs];   stats.registerSrc(rs);  //Argument 1 is regFile[rs]
               
               
               if(regFile[rs] >> 16 == 0xFFFF )                 //if negative convert to Sign Magnitude
                   aluSrc2 = (simm ^ 0xffffffff)+1;
               else
                   aluSrc2 = simm;                     stats.registerSrc(rt);         //If positive keep the value
               
               storeData = regFile[rt];                         //store value of regFile[rt] into new address
               stats.countMemOp();
               break;  // same comment as lw
    default: cerr << "unimplemented instruction: pc = 0x" << hex << pc - 4 << endl;
  }
  D(cout << endl);
}

void CPU::execute() {
  aluOut = alu.op(aluOp, aluSrc1, aluSrc2);
}

void CPU::mem() {
    uint32_t addr = pc & 0x3FFFFFFF; //addr equals 26 bits of instr (0-25)
    
	int cache_stalls = 0; 
    if(opIsLoad){
        writeData = dMem.loadWord(aluOut);
        ACCESS_TYPE type = LOAD; 
        int cache_stalls = cache.access(aluOut, type);
        cache.increaseLoadStore(type);
        stats.incrementStalls(cache_stalls);
    }
    else
        writeData = aluOut;

  if(opIsStore){
      dMem.storeWord(storeData, aluOut);
      ACCESS_TYPE type = STORE;
      int cache_stalls = cache.access(aluOut, type);
      cache.increaseLoadStore(type);
      stats.incrementStalls(cache_stalls);
    }
    
  
  
}

void CPU::writeback() {
  if(writeDest && destReg > 0) // skip if write is to zero register
    regFile[destReg] = writeData;
  
  if(opIsMultDiv) {
    hi = alu.getUpper();
    lo = alu.getLower();
  }
}

void CPU::printRegFile() {
  cout << hex;
  for(int i = 0; i < NREGS; i++) {
    cout << "    " << regNames[i];
    if(i > 0) cout << "  ";
    cout << ": " << setfill('0') << setw(8) << regFile[i];
    if( i == (NREGS - 1) || (i + 1) % 4 == 0 )
      cout << endl;
  }
  cout << "    hi   : " << setfill('0') << setw(8) << hi;
  cout << "    lo   : " << setfill('0') << setw(8) << lo;
  cout << dec << endl;
}

void CPU::printFinalStats() {
    double MemOps = instructions;
    
  cout << "Program finished at pc = 0x" << hex << pc << "  ("
       << dec << instructions << " instructions executed)" << endl << endl;
  cout << "Cycles: " << stats.getCycles() << endl; 
  cout << fixed << setprecision(2) << "CPI: " << 1.00* stats.getCycles() / instructions << endl << endl;
  cout << "Bubbles: " << stats.getBubbles() << endl;
  cout << "Flushes: " << stats.getFlushes() << endl;
  cout << "Stalls: " << stats.getStalls() << endl << endl;
  cache.printFinalStats(); 
  
}
