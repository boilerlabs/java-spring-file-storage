# 🚀 Java Spring Boot File Storage

![License](https://img.shields.io/github/license/boilerlabs/java-spring-file-storage)
![Issues](https://img.shields.io/github/issues/boilerlabs/java-spring-file-storage)
![Stars](https://img.shields.io/github/stars/boilerlabs/java-spring-file-storage)

A boilerplate starter project for building a Java Spring Boot application with File Storage Service. This template includes essential features for implementing a simple file storage service.

## Table of Contents
1. [Features](#features)
2. [Getting Started](#getting-started)
3. [Usage](#usage)
4. [Contributing](#contributing)
5. [License](#license)

## Features
- File Storage Service
- File Upload
- File Download
- File Deletion
- File Metadata
- List Files with Pagination
- Swagger UI
- Logging with Logback
- Docker Compose for MySQL and Adminer

## Getting Started

### Prerequisites
- Java 17+
- Maven

### Installation
1. Clone the repository:

```bash
git clone https://github.com/boboilerlabs/java-spring-file-storage
cd java-spring-file-storage
```

2. Running with Maven

```bash
mvn spring-boot:run
```

3. Using Swagger UI

Visit [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to access the Swagger UI and try out the API endpoints.

## Usage

### Upload File
To upload a file, send a POST request to `/api/files/upload` with the file as a form-data:

```bash
curl -X POST "http://localhost:8080/api/files/upload" \
  -H "Content-Type: multipart/form-data" \
  -F "file=@/path/to/your/file.txt"
```

### Download File

To download a file, send a GET request to `/api/files/download/{fileId}`:

```bash
curl -X GET "http://localhost:8080/api/files/download/{fileId}" -o downloaded_file.txt
```

### Delete File

To delete a file, send a DELETE request to `/api/files/delete/{fileId}`:

```bash
curl -X DELETE "http://localhost:8080/api/files/delete/{fileId}"
```

### List Files

To list all files, send a GET request to `/api/files`:

```bash
curl -X GET "http://localhost:8080/api/files?page=0&size=10"
```

### Get File Metadata

To get the metadata of a file, send a GET request to `/api/files/{fileId}`:

```bash
curl -X GET "http://localhost:8080/api/files/{fileId}"
```

## Contributing
We welcome contributions! Please see the [CONTRIBUTING.md](./CONTRIBUTING.md) for more details.

## License
This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.