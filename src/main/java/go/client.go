package main

import "C"

import (
	"fmt"
	"github.com/chubaofs/chubaofs/sdk/data/stream"
	"github.com/chubaofs/chubaofs/sdk/meta"
	"github.com/chubaofs/chubaofs/util/errors"
	//"github.com/chubaofs/chubaofs/util/log"
	"strings"
	"strconv"
)

const (
	status_ok = 0
	status_error = -1
)

const (
	volumeName  = "cfstest"
	volumeOwner = "cfs"
	masterStr     = ""
	masterSplit = ","
	namePrefix = "f-"
)
/*
const (
	volumeName  = "cfstest"
	volumeOwner = "cfs"
  masterStr     = "11.51.28.251:8080,11.51.29.2:8080,11.51.29.9:8080"
  masterSplit = ","
  namePrefix = "f-"
)

const (
	volumeName  = "ltptest"
	volumeOwner = "ltptest"
  masterStr     = "sparkchubaofs.jd.local"
  masterSplit = ","
  namePrefix = "f-"
)
*/

var (
	mw *meta.MetaWrapper
	ec *stream.ExtentClient
)

//export NewClient
func NewClient() int32 {
	var masters = strings.Split(masterStr, masterSplit)
	var metaConfig = &meta.MetaConfig{
		Volume:        volumeName,
		Owner:         volumeOwner,
		Masters:       masters,
		ValidateOwner: true,
	}
	var err error
	mw, err = meta.NewMetaWrapper(metaConfig)
	if err != nil {
		e := errors.Trace(err, "NewMetaWrapper failed!")
		fmt.Println(errors.Stack(e))
		fmt.Println("Failed to new MetaWrapper.")
		return status_error
	}

	var extentConfig = &stream.ExtentConfig{
		Volume:            volumeName,
		Masters:           masters,
		FollowerRead:      false,
		OnAppendExtentKey: mw.AppendExtentKey,
		OnGetExtents:      mw.GetExtents,
		OnTruncate:        mw.Truncate,
	}
	ec, err = stream.NewExtentClient(extentConfig)
	if err != nil {
		e := errors.Trace(err, "NewExtentClient failed!")
		fmt.Println(errors.Stack(e))
		fmt.Println("Failed to new MetaExtentClient.")
		return status_error
	}

	fmt.Printf("MetaWrapper: %v\nExtentClient: %v\n\n", mw, ec)
	return status_ok
}

//func Create(pid uint64, name *string) int64 {
//export Create
func Create(pid uint64, index int) int64 {
	//func Create(pid int64) (int64) {
	//n := *name
	//fmt.Printf("PrintPointer %v %v\n", n, *name)
	info, err := mw.Create_ll(uint64(pid), namePrefix+strconv.Itoa(index), 755, 1006, 1006, nil)
	if err != nil {
		//log.LogErrorf("Create: pid(%v) index(%v), err(%v)", pid, index, err)
		fmt.Printf("Create: pid(%v) index(%v), err(%v)", pid, index, err)
		return status_error
	}
	return int64(info.Inode)
}

//export Open
func Open(iid int64 ) int32 {
	ec.OpenStream(uint64(iid))
	ec.RefreshExtentsCache(uint64(iid))
	return status_ok
}

//export Write
//func Write(iid uint64, offset int32, data *string) (int32, error) {
func Write(iid int64, offset int64 , data *string) (int32) {
	ino := uint64(iid)
	datalen := len(*data)

	size, err := ec.Write(ino, int(offset), []byte(*data), false)
	if err != nil {
		fmt.Sprintf("Write: ino(%v) offset(%v) len(%v) err(%v)", ino, offset, datalen, err)
		return status_error
		//return -1, nil
	}

	if size != datalen {
		fmt.Printf("Write: ino(%v) offset(%v) len(%v) size(%v)", ino, offset, datalen, size)
		return status_error
		//return -1, nil
	}

	return int32(size)
}

//export Close
func Close(iid int64) int32 {
	err := ec.CloseStream(uint64(iid))
	if err != nil {
		fmt.Printf("Release: close writer failed, ino(%v)  err(%v)", iid, err)
		return status_error
	}
	return status_ok
}

func main() {}
