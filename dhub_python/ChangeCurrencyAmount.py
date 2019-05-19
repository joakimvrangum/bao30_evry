import mysql.connector
import random

mydb = mysql.connector.connect(
  host="vrangum.com",
  user="vrangum2_audun",
  passwd="Audi9999",
  database="vrangum2_dhub"
)

SQLListe = []

cursor = mydb.cursor()
cursor = mydb.cursor(buffered=True)
mydb.autocommit = True
cursor.execute("SELECT * FROM ACCOUNT")
for round in cursor:
    if round != None:

        randomVerdi = random.randint(0, 10)

        if(round[2] == "KAPTIALKONTO"):
            if(randomVerdi == 0):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 10000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
            elif(randomVerdi == 1):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 20000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
            elif(randomVerdi == 2):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 30000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
            elif(randomVerdi == 3):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 40000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
            elif(randomVerdi == 4):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 50000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
            elif(randomVerdi == 5):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 60000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
            elif(randomVerdi == 6):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 70000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
            elif(randomVerdi == 7):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 100000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
            elif(randomVerdi == 8):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 150000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
            elif(randomVerdi == 9):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 200000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
            elif(randomVerdi == 10):
                SQLListe.append("UPDATE ACCOUNT SET SALDO_VAL = " + str(random.randint(0, 2000000)) + " WHERE BK_KONTO_NR = " + str(round[0]))
        

counter = 0

for SQLSpørring in SQLListe:
    cursor.execute(SQLSpørring)
    print(SQLSpørring)
    counter = counter + 1
    print(counter)