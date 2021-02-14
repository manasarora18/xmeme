# Xmeme
A Full stack application created to post memes on a feed and able to see them.

## Technology stack
FrontEnd - React JS, HTML, CSS, Javascript

Backend - Spring-Boot, Java

Database - Postgres

## Backend overview
Xmeme is implemented in a Microservice architecture
(inside controller folder class XmemeController)

1. findById which takes id as parameter and returns the meme which have that id.

2. findAllMemes which takes no parameters and returns the all the memes with limit to 100

3. addMeme which takes MemeDTO(name,url,caption) data as parameter and save the data into database followed by returning the newly created id.

4. updateMeme which takes id and MemeDTO(url,caption) as parameter and updates the url and caption for a specific field.
Components which is used to implement the APIs
Meme (an entity class) is used to map json data in entities.

MemeRepo is a interface which extends the Crud Operations of PostgreSQL.

Publicly Deployed Link for this project
https://xmeme-be.herokuapp.com/

End-Point = /memes
For curl commands
1. For Post request


curl --location --request POST 'https://xmeme-be.herokuapp.com/memes' \

--header 'Content-Type: application/json' \

--data-raw '{

"name": "MS Dhoni",

"url": "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg",

"caption": "This is a meme"

}'


2. For Get request

curl --location --request GET 'https://xmeme-be.herokuapp.com/memes'

curl --location --request GET 'https://xmeme-be.herokuapp.com//memes/<id>'


3. For Patch request


curl --location --request PATCH 'https://xmeme-be.herokuapp.com/memes/<id>'

--header 'Content-Type: application/json' \

--data-raw '{

"url": "new_url",

"caption": "new_caption"

}'
