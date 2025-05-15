# Identity Service

Identity Service là một dự án Java Spring Boot chịu trách nhiệm cung cấp các chức năng về xác thực người dùng, quản lý token, và bảo mật ứng dụng.

## Mục lục

1. [Giới thiệu](#giới-thiệu)
2. [Các tính năng](#các-tính-năng)
3. [Cách sử dụng](#cách-sử-dụng)
4. [Cài đặt và cấu hình](#cài-đặt-và-cấu-hình)
5. [Cấu trúc dự án](#cấu-trúc-dự-án)
6. [Cài đặt môi trường](#cài-đặt-môi-trường)
7. [Liên hệ](#liên-hệ)

## Giới thiệu

Identity Service là dịch vụ trung tâm xử lý xác thực và quản lý danh tính người dùng trong hệ thống. Nó hỗ trợ việc tạo token JWT, xác minh, làm mới token, và xử lý yêu cầu logout nhằm đảm bảo an toàn cho hệ thống.

## Các tính năng

- **Xác thực người dùng**: Xử lý thông tin đăng nhập và xác thực qua token.
- **Quản lý token JWT**: Hỗ trợ tạo mới, cập nhật, và làm mới token.
- **Logout**: Thu hồi token khỏi danh sách hợp lệ.
- **Kiểm tra token**: Xác minh tình trạng và tính hợp lệ của token.

## Cách sử dụng

### Endpoints chính

1. **Authenticate**  
   Endpoint: `/authenticate`  
   Mô tả: Xác thực thông qua tài khoản người dùng (username và password).  
   Phương thức: `POST`  
   Request Body mẫu:
   ```json
   {
       "username": "exampleUser",
       "password": "examplePassword"
   }
   ```
   Response mẫu:
   ```json
   {
       "accessToken": "jwt-access-token",
       "refreshToken": "jwt-refresh-token",
       "expiresIn": 3600
   }
   ```

2. **Refresh Token**  
   Endpoint: `/refresh-token`  
   Mô tả: Tạo token mới bằng cách sử dụng token refresh (JWT).  
   Phương thức: `POST`

3. **Logout**  
   Endpoint: `/logout`  
   Mô tả: Thu hồi token hiện tại.

4. **Introspect Token**  
   Endpoint: `/introspect`  
   Mô tả: Kiểm tra tình trạng của token.

## Cài đặt và cấu hình

### Yêu cầu

- **Java**: JDK 23+
- **Spring Boot**: 3.x
- **Cơ sở dữ liệu**: Hibernate và các token bị vô hiệu hóa.

### Cách chạy:

1. Clone repository:
   ```bash
   git clone <repository-url>
   cd identity-service
   ```

2. Chạy ứng dụng:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Ứng dụng mặc định chạy tại: `http://localhost:8080`

## Cấu trúc dự án

Dự án bao gồm các thành phần sau:

- **`service`**: Chứa `AuthenticationService` đảm nhận các logic liên quan tới xác thực.
- **`repository`**: Quản lý các truy vấn và lưu trữ dữ liệu.
- **`controller`**: Xử lý các request/response của người dùng.

## Cấu hình

Cấu hình JWT:

```yaml
jwt:
  signerKey: "your-signer-key"
  valid-duration: 3600
  refreshable-duration: 86400
```

## Liên hệ
- **Integration Test**: Test Container, jupiter
- **Spotless**: format code
- **SonarLint**: hỗ trợ code
- **SonarQube**: hỗ trợ code
- **Tác giả**: Khangdjnh
- **Email**: support@devteria.com
