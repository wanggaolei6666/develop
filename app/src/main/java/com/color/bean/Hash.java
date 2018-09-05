package com.color.bean;


/**
 * Created by wanggaolei on 2018/8/21.
 */

public class Hash {
    private String FileHash;

    public Hash(String fileHash, String albumID, String extName, String fileName) {
        FileHash = fileHash;
        AlbumID = albumID;
        ExtName = extName;
        FileName = fileName;
    }

    public Hash(String fileHash, String albumID) {
        FileHash = fileHash;
        AlbumID = albumID;
    }

    private String AlbumID;

    public String getFileHash() {
        return FileHash;
    }

    public void setFileHash(String fileHash) {
        FileHash = fileHash;
    }

    public String getAlbumID() {
        return AlbumID;
    }

    public void setAlbumID(String albumID) {
        AlbumID = albumID;
    }

    public String getExtName() {
        return ExtName;
    }

    public void setExtName(String extName) {
        ExtName = extName;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    private String ExtName;
    private String FileName;
}
