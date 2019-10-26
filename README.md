E-learning System 
------------------

Tools used 

 - maven 
 - eclipse 
 - postman
 

 Security 

 - spring security 
 - Bearer JWT 


 Logging 

 - logback xml ( with archived mechanism per day / per configurable file size ) 
 - logs files are ignored from git using .gitignore config file .
 

 Exception Handling 

 - Using spring boot Controller Advice ( Ahtuntecation , authorization , syntax errors , validation , business and Unknown Exceptions)  


 Database

 - H2 embeded inMemory database ( you can use h2 console on localhost:8080/h2 < doesn't need any kind of security to Make it easy for Assesment purpose > 

 
 Documentation 

 - Swagger 2 ( you can access swagger ui on http://localhost:8080/swagger-ui.html#/ ) 

-----------------------------------------------------------------------------------------------------------------------------------------------------

 How To build and Use :- 
------------------------- 

 This is maven based project so you can build it with maven using goal clean install then run it as spring boot application. 

 - there is 2 types of users with different roles : 

    Admin
    User

  You can register new user (student) using student controller ( see Swagger UI ) .
  
  then you can login with registered student if success login it will back with token then you will use this token in Authorization request header
         to register student on courses and unregister and get all courses .

 If you use Swagger UI to excute the services you will need to click on Authorize button and add this string "Bearer " then genrated token .

 Note that :
         you will need to add courses first but add course end point only admin can use it so if you need to add courses you must login as admin and here are static admin credintials 

           "email" : "admin@gmail.com"
           "password":"admin123"

 Roles and admin user are created on startup of application .

 Important note I use Bearer token so you will need to add this string in front of generated token "Bearer "
