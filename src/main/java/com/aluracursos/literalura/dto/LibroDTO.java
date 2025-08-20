package com.aluracursos.literalura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LibroDTO {
    private String title;

    private List<String> languages;

    @JsonProperty("download_count")
    private int downloadCount;

    private List<AutorDTO> authors;

    public LibroDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public List<AutorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AutorDTO> authors) {
        this.authors = authors;
    }
}
