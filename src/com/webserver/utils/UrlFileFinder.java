package com.webserver.utils;

import com.webserver.constants.WebConstants;
import com.webserver.enums.StatusCodes;
import com.webserver.objects.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UrlFileFinder {
    private boolean isAsset;
    private Path filePath;
    private String mimeType;
    private byte[] bytes;

    public UrlFileFinder(String path) throws IOException {
        this.isAsset = path.contains(".");

        if (!isAsset) {
            filePath = Paths.get(WebConstants.RESOURCES_FOLDER_PATH,"pages", path + ".html");
        } else {
            filePath = Paths.get(WebConstants.RESOURCES_FOLDER_PATH,"assets", path);
        }

        this.mimeType = Files.probeContentType(filePath);

        this.bytes = Files.readAllBytes(filePath);
    }

    public boolean getIsAsset() {
        return this.isAsset;
    }

    public Path getFilePath() {
        return this.filePath;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public int getPayloadLength() {
        if (this.isAsset) {
            return this.bytes.length;
        } else {
            return new String(this.bytes).length();
        }
    }
}
