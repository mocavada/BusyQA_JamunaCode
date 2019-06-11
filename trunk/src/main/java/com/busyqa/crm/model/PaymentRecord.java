package com.busyqa.crm.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "client_payment_records")
public class PaymentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientCourse;

    private double amountPaid;

    private double totalAmount;

    private String lastPaidDate;

    private String nextPaymentDate;

    private String isRegisteredStudent;

    private String trainingLocation;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teams_id", nullable = false, updatable = false)
    private Team team;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_status_id", nullable = true, updatable = true)
    private ClientStatus clientStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_plan_id", nullable = true, updatable = true)
    private PaymentPlan paymentPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_status_id", nullable = true, updatable = true)
    private PaymentStatus paymentStatus;

    public PaymentRecord(User user, Team team, ClientStatus clientStatus, PaymentPlan paymentPlan, PaymentStatus paymentStatus){
        this.user = user;
        this.team = team;
        this.clientStatus = clientStatus;
        this.paymentPlan = paymentPlan;
        this.paymentStatus = paymentStatus;
    }

    public PaymentRecord(){}


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public PaymentPlan getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getLastPaidDate() {
        return lastPaidDate;
    }

    public void setLastPaidDate(String lastPaidDate) {
        this.lastPaidDate = lastPaidDate;
    }

    public String getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public String getClientCourse() {
        return clientCourse;
    }

    public void setClientCourse(String clientCourse) {
        this.clientCourse = clientCourse;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTrainingLocation() {
        return trainingLocation;
    }

    public void setTrainingLocation(String trainingLocation) {
        this.trainingLocation = trainingLocation;
    }

    public String getIsRegisteredStudent() {
        return isRegisteredStudent;
    }

    public void setIsRegisteredStudent(String isRegisteredStudent) {
        this.isRegisteredStudent = isRegisteredStudent;
    }
}

