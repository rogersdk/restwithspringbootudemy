package br.com.erudio.data.vo.v1;

import java.io.Serializable;

public class UploadFileResponseVO implements Serializable {

	private static final long serialVersionUID = 3946494616355063294L;

	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private Long size;

	public UploadFileResponseVO() {
	}

	public UploadFileResponseVO(String fileName, String fileDownloadUri, String contentType, long size) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = contentType;
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
