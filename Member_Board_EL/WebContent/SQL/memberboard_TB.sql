create table memberboard(
BOARD_NUM   NUMBER(3,0),    
BOARD_ID   VARCHAR2(20 BYTE),    
BOARD_SUBJECT   VARCHAR2(20 BYTE),   
BOARD_CONTENT   VARCHAR2(20 BYTE),  
BOARD_FILE   VARCHAR2(4000 BYTE),  
BOARD_RE_REF   NUMBER(3,0),  
BOARD_RE_LEV   NUMBER(3,0),  
BOARD_RE_SEQ   NUMBER(3,0),    
BOARD_READCOUNT   NUMBER(3,0),   
BOARD_DATE   DATE ) ;

