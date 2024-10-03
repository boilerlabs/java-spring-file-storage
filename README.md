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
- File Upload
- File Download
- List Files
- File Deletion
- File Storage Service

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

## Usage

### Upload File
To upload a file, send a POST request to `/api/files/upload` with the file as a form-data:

```bash
curl -X POST http://localhost:8080/api/files/upload \
     -H "Authorization: B
        -F "file=@/path/to/file"
```

> You can also try to upload a file using the Swagger UI by visiting `http://localhost:8080/swagger-ui.html`

### Download File

To download a file, send a GET request to `/api/files/download/{fileId}`:

```bash
curl -X GET http://localhost:8080/api/files/download/{fileId} \
     -H "Authorization
```

## Contributing
We welcome contributions! Please see the [CONTRIBUTING.md](./CONTRIBUTING.md) for more details.

## License
This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.