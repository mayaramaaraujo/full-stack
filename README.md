# Full-Stack

## 1. Object Orientated Programming
- Path: `note-api/src/main/java/com/feefo/noteapi/utils/JobTitleNormalizer.java`

## 2. UI Assessment
- Path: `ui-assessment-master`

## 3. Web App Restful API System Design

### API Documentation

#### 1. Endpoint: POST /v1/notes
- **Description:** Create a new note.
- **Request Body:**
  ```json
  {
    "note": "string"
  }
  ```
- **Response Codes:**
  - `201 Created`: The note was created successfully.
    ```json
    {
      "id": 1,
      "note": "Example note"
    }
    ```
  - `400 Bad Request`: Invalid request, the request body is missing or malformed.
    ```json
    {
      "error": "The request body must contain a valid 'note' field."
    }
    ```

#### 2. Endpoint: GET /v1/notes
- **Description:** Retrieve all notes.
- **Response Codes:**
  - `200 OK`: Successful request.
    ```json
    [
      {
        "id": 1,
        "note": "Note 1"
      },
      {
        "id": 2,
        "note": "Note 2"
      }
    ]
    ```

#### 3. Endpoint: GET /v1/notes/{id}
- **Description:** Retrieve a specific note by its ID.
- **Response Codes:**
  - `200 OK`: Successful request.
    ```json
    {
      "id": 1,
      "note": "Note 1"
    }
    ```
  - `404 Not Found`: Note not found.
    ```json
    {
      "error": "Note not found for the provided ID."
    }
    ```

#### 4. Endpoint: DELETE /v1/notes/{id}
- **Description:** Delete a note by its ID.
- **Response Codes:**
  - `204 No Content`: Note deleted successfully.
  - `404 Not Found`: Note not found.
    ```json
    {
      "error": "Note not found for the provided ID."
    }
    ```

## 3.1 High level design
<img width="612" alt="Captura de Tela 2024-05-20 às 15 49 49" src="https://github.com/mayaramaaraujo/full-stack/assets/63872581/c3fba62c-a938-416c-b6ff-80be368bf819">

