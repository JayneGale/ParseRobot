public enum ExpType {
//stage 2    EXP   ::= NUM | SEN | OP "(" EXP "," EXP ")"
//stage 4    EXP   ::= NUM | EXP OP EXP |  SEN | VAR | "(" EXP ")"

    num, sen, exp, op, var, openParen;
}
