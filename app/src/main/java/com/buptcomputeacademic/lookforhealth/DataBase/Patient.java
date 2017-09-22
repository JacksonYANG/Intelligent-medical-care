package com.buptcomputeacademic.lookforhealth.DataBase;

import org.litepal.crud.DataSupport;

/**
 * Created by 35390 on 2017/9/22.
 */

public class Patient extends DataSupport {

    private String patient_name;
    private String patient_age;
    private String patient_sex;
    private String patient_phone;
    private String patient_height;
    private String patient_weight;
    private String patient_disease;
    private String patient_bp;//患者血压数据
    private String patient_ecg;//患者心电数据

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_sex() {
        return patient_sex;
    }

    public void setPatient_sex(String patient_sex) {
        this.patient_sex = patient_sex;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }

    public String getPatient_height() {
        return patient_height;
    }

    public void setPatient_height(String patient_height) {
        this.patient_height = patient_height;
    }

    public String getPatient_weight() {
        return patient_weight;
    }

    public void setPatient_weight(String patient_weight) {
        this.patient_weight = patient_weight;
    }

    public String getPatient_disease() {
        return patient_disease;
    }

    public void setPatient_disease(String patient_disease) {
        this.patient_disease = patient_disease;
    }

    public String getPatient_bp() {
        return patient_bp;
    }

    public void setPatient_bp(String patient_bp) {
        this.patient_bp = patient_bp;
    }

    public String getPatient_ecg() {
        return patient_ecg;
    }

    public void setPatient_ecg(String patient_ecg) {
        this.patient_ecg = patient_ecg;
    }
}
