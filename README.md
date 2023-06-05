## Import the project into Intellij

If you want to import these project into the local eclipse setup -

  1. Download the project as zip file into your computer
  2. Unzip the project at your desired location
  3. Open the project into intellij

## Test API in Postman

### GET  
    curl --location 'localhost:8080/api'
### POST 
    
    curl --location 'localhost:8080/api' \
    --header 'Content-Type: application/json' \
    --data '{
        "start_date": "2020-06-15 10:00:00",
        "product_id": 35455,
        "brand_id": 1
    }'
    
## Admin Database H2

  * Run project
  * Open browser
     * http://localhost:8080/h2-ui
