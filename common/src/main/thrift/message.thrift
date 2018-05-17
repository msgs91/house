namespace java house.thrift

struct Message {
    2: required i32 version
    3: required i32 nodeId
    4: required string msg
}

struct Entry {
    1: required i32 version
    2: required string key
    3: required string value
}

