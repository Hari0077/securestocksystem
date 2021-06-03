# securestocksystem

Technologies used
 
    Android -(JAVA)
    Cloud Firestore
    
  Algorithm used:
   
    DATA ENCRYPTION STANDARD 
    SHA1 ALGORITHM
    
 securestocksystem overview : This system enables the user to login securely and able to add products , these products are encrypted using DES Algorithm and stored in cloud firestore.
 and able to view products only user enters valid key.
 
 
 Registration Module and Login Module:
      
      user should give username , password and key. These details are hashed using SHA1 Algorithm and stored in cloud firestore. while login user should give correct username and 
      password these credentials will be hashed using SHA1 algorithm and check with cloud firestore if hashed details are equal , it enables user to enter into system.
       
Add Products Module:

      user able to add products , these products will be encrypted using DATA ENCRYPTION STANDARD and stored in the cloud firestore.
    
View Products Module:

     user able to view all the products when user gives valid key.these product details are decrypted using DES algorithm.
     
     
