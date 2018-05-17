namespace java house.thrift

service ClientService {
    bool write(1:string key, 2:string value)

    string read(1:string key)
}


service TestService {
    bool write(1:string key, 2:string value)

    string read(1:string key)
}

service PeerService {
    bool replicate(1:string key, 2:string value)

}


//service Test {
//    bool ping(),
//
//    bool createNode(1:string path, 2:string data, 3:i64 version),
//
//    bool deleteNode(1:string path),
//
//    bool exists(1:string path),
//
//    bool updateData(1:string path, 2:string data, 3:i64 version),
//
//    string getData(1:string path),
//
//    bool setWatcher(1:string path)
//}