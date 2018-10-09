data segment

ends

stack segment
    dw   128  dup(0)
ends

code segment
start:

;
; suskaiciuoti     /   a+c^2             , kai c = 2*x
;              y = |   |b-2*x|           , kai c < 2*x
;                  \   ](3*c+x)/(c-2*x)[ , kai c > 2*x
; skaiciai su zenklu
; Duomenys a - b, b - w, c - b, x - w, y - w  

stekas  SEGMENT STACK
	DB 256 DUP(0)
stekas  ENDS

duom    SEGMENT 
a	DB  2  ;   10000; perpildymo situacijai 
b	DW -2
c	DB 8
x	DW -1,-2,-4,12,9,45,6
kiek	= ($-x)/2
y	DW kiek dup(0AAh)     
isvb	DB 'x=',6 dup (?), ' y=',6 dup (?), 0Dh, 0Ah, '$'
perp	DB 'Perpildymas', 0Dh, 0Ah, '$'
daln	DB 'Dalyba is nulio', 0Dh, 0Ah, '$'
netb	DB 'Netelpa i baita', 0Dh, 0Ah, '$'
spausk  DB 'Skaiciavimas baigtas, spausk bet kuri klavisa,', 0Dh, 0Ah, '$' 
duom    ENDS




mov ax, 4c00h
int 21h  

ends

end start
