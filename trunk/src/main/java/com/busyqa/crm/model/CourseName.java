package com.busyqa.crm.model;

public enum CourseName {
  AUTOMATES_TESTING("AUTOMATION_TESTING", 2000.00),
  AUTOMATION_TESTING_ONLINE("AUTOMATION_TESTING_ONLINE", 1000.00),
  BUSINESS_ANALYSIS("BUSINESS_ANALYSIS", 2500.00),
  BIGDATA_DATA_SCIENCE("BIGDATA_DATA_SCIENCE", 1000.00),
  SCRUM_MASTER("SCRUM_MASTER", 2200.00),
  FULL_STACK_JAVA_DEV("FULL_STACK_JAVA_DEV", 3500.00),
  PERFORMANCE_TESTING("PERFORMANCE_TESTING", 1000.00),
  NOT_DECIDED("NOT_DECIDED", 0.00);

  public String courseName;
  public double courseCost;

  CourseName(String courseName, double courseCost) {
    this.courseName = courseName;
    this.courseCost = courseCost;
  }

  public String getCourseName() {
    return courseName;
  }

  public double getCourseCost() {
    return courseCost;
  }
}
