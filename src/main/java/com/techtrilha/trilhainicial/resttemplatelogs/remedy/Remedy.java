package com.techtrilha.trilhainicial.resttemplatelogs.remedy;

public class Remedy {

  private Long id;
  private String name;
  private String description;
  private String pharmaceuticalId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPharmaceuticalId() {
    return pharmaceuticalId;
  }

  public void setPharmaceuticalId(String pharmaceuticalId) {
    this.pharmaceuticalId = pharmaceuticalId;
  }

}