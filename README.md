# SpringBoot整合TrueLicense生成和验证License证书

# 一、简介

- spring-api-commons：通用模块
- license-server：获取服务器硬件信息并生成 License 证书模块
- license-client：验证 License 证书模块

# 二、使用步骤

- 在存放 License 证书的目录栏上输入 CMD，然后回车
- 在 cmd.exe 执行如下命令（使用 Keytool 生成公私钥证书库）：

```bash
# 1.生成私匙库
# validity：私钥的有效期多少天
# alias：私钥别称
# keystore: 指定私钥库文件的名称（生成在当前目录）
# storepass：指定私钥库的密码（获取keystore信息所需的密码）
# keypass：指定别名条目的密码（私钥的密码）
keytool -genkeypair -keysize 1024 -validity 3650 -alias "privateKey" -keystore "privateKeys.keystore" -storepass "public_password1234" -keypass "private_password1234" -dname "CN=localhost, OU=localhost, O=localhost, L=SH, ST=SH, C=CN"

# 2.把私匙库内的公匙导出到一个文件当中
# alias：私钥别称
# keystore：指定私钥库的名称（在当前目录查找）
# storepass: 指定私钥库的密码
# file：证书名称
keytool -exportcert -alias "privateKey" -keystore "privateKeys.keystore" -storepass "public_password1234" -file "certfile.cer"

# 3.再把这个证书文件导入到公匙库
# alias：公钥别称
# file：证书名称
# keystore：公钥文件名称
# storepass：指定私钥库的密码
keytool -import -alias "publicCert" -file "certfile.cer" -keystore "publicCerts.keystore" -storepass "public_password1234"
```

- 修改 license-server 的配置文件 application-dev.yaml 中证书存放位置，然后启动

- 获取服务器硬件信息

    - 通过 Postman 访问：http://localhost:8080/mobile/license/getServerInfos

- 生成 License 证书

    - 通过 Postman 访问：http://localhost:8080/mobile/license/generateLicense

    - 请求时需要在 Header 中添加一个 Content-Type ，其值为：application/json;charset=UTF-8

    - 请求参数如下：

        ```json
        {
        	"subject": "license_demo",
        	"privateAlias": "privateKey",
        	"keyPass": "private_password1234",
        	"storePass": "public_password1234",
        	"licensePath": "C:/Users/Administrator/Desktop/test/license.lic",
        	"privateKeysStorePath": "C:/Users/Administrator/Desktop/test/privateKeys.keystore",
        	"issuedTime": "2022-12-13 00:00:01",
        	"expiryTime": "2022-12-31 23:59:59",
        	"consumerType": "User",
        	"consumerAmount": 1,
        	"description": "这是证书描述信息",
        	"licenseCheckModel": {
        		"ipAddress": [],
        		"macAddress": [],
        		"cpuSerial": "",
        		"mainBoardSerial": ""
        	}
        }
        ```

        注：licenseCheckModel 中的内容通过上一步获取的服务器硬件信息来填写，可加可不加，加了就会验证，不加就不验证

- 关闭 license-server，修改 license-client 的配置文件 application-dev.yaml 中 License  证书信息，这些信息时上一步生成 License 证书时的请求参数