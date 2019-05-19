import mysql.connector

mydb = mysql.connector.connect(
  host="vrangum.com",
  user="vrangum2_audun",
  passwd="Audi9999",
  database="vrangum2_dhub"
)

autoInc = 1

testlist = []

SQLListe = []

cursor = mydb.cursor()
cursor = mydb.cursor(buffered=True)
mydb.autocommit = True
cursor.execute("SELECT * FROM CUSTOMER WHERE AGE < 34")
for round in cursor:
    #print(round)
    if round != None:
        #print(round)
        #print ("UPDATE BSU_ACCOUNT SET KUNDE_NR = " + str(round[0]) + " WHERE ID = " + str(autoInc))
        SQLListe.append("UPDATE BSU_ACCOUNT SET KUNDE_NR = " + str(round[0]) + " WHERE ID = " + str(autoInc))
        testlist.append(round[0])
        #cursor.execute("UPDATE BSU_ACCOUNT SET KUNDE_NR = " + str(round[0])+ " WHERE ID = " + str(autoInc))
        #cursor.execute("SELECT * FROM CUSTOMER WHERE AGE < 34")
        autoInc = autoInc + 1
        

for SQLSpørring in SQLListe:
    cursor.execute(SQLSpørring)
    print(SQLSpørring)