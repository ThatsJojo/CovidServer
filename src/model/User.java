package model;

import java.util.Objects;

public class User {

    private String key;
    private String name;
    private String age;
    private String sex;
    private String temperature;
    private String breathingRate;
    private String heartRate;
    private String oxygenSaturation;
    private String systolicBloodPressure;
    private String diastolicBloodPressure;
    private String date;
    private String time;
    private boolean hasUnseenAllert;

    /**
     * Cria um usuário
     * @param key Chave do usuário.
     * @param name Nome do usuário.
     * @param age Idade do usuário.
     * @param sex Sexo do usuário.
     * @param temperature Temperatura do usuário.
     * @param breathingRate Taxa respiratória do usuário.
     * @param heartRate Frequência Cardíaca do usuário.
     * @param oxygenSaturation Saturação de oxigênio do usuário.
     * @param systolicBloodPressure Pressão sanguínea sistólica do usuário.
     * @param diastolicBloodPressure Pressão sanguínea diastólica do usuário.
     * @param date Data da última atualização dos dados do usuário.
     * @param time Horário da última atualização dos dados do usuário.
     */
    public User(String key, String name, String age, String sex, String temperature, String breathingRate, String heartRate, String oxygenSaturation, String systolicBloodPressure, String diastolicBloodPressure, String date, String time) {
        this.key = key;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.temperature = temperature;
        this.breathingRate = breathingRate;
        this.heartRate = heartRate;
        this.oxygenSaturation = oxygenSaturation;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
        this.date = date;
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }

    /**
     * Retorna as informações do usuário formatas para serem enviadas via TCP Response.
     * @return 
     */
    public String getData() {
        return  key + "\n"
                + name + "\n"
                + age + "\n"
                + sex + "\n"
                + temperature + "\n"
                + breathingRate + "\n"
                + heartRate + "\n"
                + oxygenSaturation + "\n"
                + systolicBloodPressure + "\n"
                + diastolicBloodPressure + "\n"
                + date + "\n"
                + time + "\n";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getBreathingRate() {
        return breathingRate;
    }

    public void setBreathingRate(String breathingRate) {
        this.breathingRate = breathingRate;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getOxygenSaturation() {
        return oxygenSaturation;
    }

    public void setOxygenSaturation(String oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation;
    }

    public String getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(String systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public String getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(String diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    public boolean isHasUnseenAllert() {
        return hasUnseenAllert;
    }

    public void setHasUnseenAllert(boolean hasUnseenAllert) {
        this.hasUnseenAllert = hasUnseenAllert;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
