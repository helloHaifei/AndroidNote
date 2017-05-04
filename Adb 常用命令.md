# ADB 常用命令


# dumpsys
### 读取所有服务的运行情况
> adb shell dumpsys

### 设备的分辨率时
> $ adb shell dumpsys window displays


# getprop
### 读取配置文件信息 （列出所有）
> adb shell getprop
### 获得IP
> $ adb shell getprop dhcp.wlan0.ipaddress
> 192.168.0.107

## 取出安装在手机中的 apk
~~~
1.将安装的所有应用包名列出来
adb shell pm list packages
2.找到 apk 的位置
adb shell pm path com.tence01.mm
3.pull 出来
adb pull /data/app/com.tence01.mm-1.apk ~/apks
~~~