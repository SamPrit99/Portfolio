
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

include "emu8086.inc"
org 100h

PrintMaze:
    mov CX, ROWS ;puts the rows in cx
    mov BX, 0 
        PrintRow:
        mov holdCount, CX ;stores row value in holdCount
        mov CX, COLS ;moves the column number to CX
        
        PrintAnother:
        mov AL, mz + BX ;moves maze and offset into AL
        putc AL
        inc BX
        loop PrintAnother
        
        printn
        mov CX, holdCount
        loop PrintRow
        

ret

;data for the maze this is what the maze will be drawn from
mz db 1,1,1,1,1,1,1,1,1,1
   db 1,1,1,0,0,0,0,0,0,3
   db 1,1,0,0,0,0,1,1,1,1
   db 1,1,0,0,0,0,1,1,1,1
   db 1,1,0,1,1,0,0,1,1,1
   db 1,1,0,0,1,1,0,1,1,1
   db 1,1,0,1,1,1,0,0,1,1
   db 1,1,1,1,1,1,0,1,1,1
   db 1,0,0,0,0,0,0,0,0,1
   db 1,2,1,1,1,1,1,1,1,1

COLS = 10 ;number of columns
ROWS = ($ - mz)/COLS ;number of rows, based on columns
holdCount dw ? ;holds the value of the row when printing maze

DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS