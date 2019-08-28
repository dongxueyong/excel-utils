package bean;


import java.math.BigDecimal;

public class DlGrainData {

  private String dataId;
  private long dataType;
  private long dataRegiontype;
  private String dataProvince;
  private String dataCity;
  private String dataCounty;
  private long grainType;
  private String grainVariety;
  private String dataYear;
  private String dataGbgrade;
  private BigDecimal dataNumber;
  private BigDecimal dataDealnumber;
  private String dataMaxprice;
  private BigDecimal dataMinprice;
  private String dataMainmaxprice;
  private String dataMainminprice;
  private BigDecimal dataAvgprice;
  private String dataTrantime;
  private String crttime;
  private String lmdtime;

  public DlGrainData() {
  }

  public DlGrainData(String province, String city, String contry, String value) {
    this.dataProvince = province;
    this.dataCity = city;
    this.dataCounty = city;

  }

  public DlGrainData(String dataId, long dataType, long dataRegiontype, String dataProvince, String dataCity, String dataCounty, long grainType, String grainVariety, String dataYear, String dataGbgrade, BigDecimal dataNumber, BigDecimal dataDealnumber, String dataMaxprice, BigDecimal dataMinprice, String dataMainmaxprice, String dataMainminprice, BigDecimal dataAvgprice, String dataTrantime, String crttime, String lmdtime) {
    this.dataId = dataId;
    this.dataType = dataType;
    this.dataRegiontype = dataRegiontype;
    this.dataProvince = dataProvince;
    this.dataCity = dataCity;
    this.dataCounty = dataCounty;
    this.grainType = grainType;
    this.grainVariety = grainVariety;
    this.dataYear = dataYear;
    this.dataGbgrade = dataGbgrade;
    this.dataNumber = dataNumber;
    this.dataDealnumber = dataDealnumber;
    this.dataMaxprice = dataMaxprice;
    this.dataMinprice = dataMinprice;
    this.dataMainmaxprice = dataMainmaxprice;
    this.dataMainminprice = dataMainminprice;
    this.dataAvgprice = dataAvgprice;
    this.dataTrantime = dataTrantime;
    this.crttime = crttime;
    this.lmdtime = lmdtime;
  }

  public String getDataId() {
    return dataId;
  }

  public void setDataId(String dataId) {
    this.dataId = dataId;
  }


  public long getDataType() {
    return dataType;
  }

  public void setDataType(long dataType) {
    this.dataType = dataType;
  }


  public long getDataRegiontype() {
    return dataRegiontype;
  }

  public void setDataRegiontype(long dataRegiontype) {
    this.dataRegiontype = dataRegiontype;
  }


  public String getDataProvince() {
    return dataProvince;
  }

  public void setDataProvince(String dataProvince) {
    this.dataProvince = dataProvince;
  }


  public String getDataCity() {
    return dataCity;
  }

  public void setDataCity(String dataCity) {
    this.dataCity = dataCity;
  }


  public String getDataCounty() {
    return dataCounty;
  }

  public void setDataCounty(String dataCounty) {
    this.dataCounty = dataCounty;
  }


  public long getGrainType() {
    return grainType;
  }

  public void setGrainType(long grainType) {
    this.grainType = grainType;
  }


  public String getGrainVariety() {
    return grainVariety;
  }

  public void setGrainVariety(String grainVariety) {
    this.grainVariety = grainVariety;
  }


  public String getDataYear() {
    return dataYear;
  }

  public void setDataYear(String dataYear) {
    this.dataYear = dataYear;
  }


  public String getDataGbgrade() {
    return dataGbgrade;
  }

  public void setDataGbgrade(String dataGbgrade) {
    this.dataGbgrade = dataGbgrade;
  }


  public BigDecimal getDataNumber() {
    return dataNumber;
  }

  public void setDataNumber(BigDecimal dataNumber) {
    this.dataNumber = dataNumber;
  }


  public BigDecimal getDataDealnumber() {
    return dataDealnumber;
  }

  public void setDataDealnumber(BigDecimal dataDealnumber) {
    this.dataDealnumber = dataDealnumber;
  }


  public String getDataMaxprice() {
    return dataMaxprice;
  }

  public void setDataMaxprice(String dataMaxprice) {
    this.dataMaxprice = dataMaxprice;
  }


  public BigDecimal getDataMinprice() {
    return dataMinprice;
  }

  public void setDataMinprice(BigDecimal dataMinprice) {
    this.dataMinprice = dataMinprice;
  }


  public String getDataMainmaxprice() {
    return dataMainmaxprice;
  }

  public void setDataMainmaxprice(String dataMainmaxprice) {
    this.dataMainmaxprice = dataMainmaxprice;
  }


  public String getDataMainminprice() {
    return dataMainminprice;
  }

  public void setDataMainminprice(String dataMainminprice) {
    this.dataMainminprice = dataMainminprice;
  }


  public BigDecimal getDataAvgprice() {
    return dataAvgprice;
  }

  public void setDataAvgprice(BigDecimal dataAvgprice) {
    this.dataAvgprice = dataAvgprice;
  }


  public String getDataTrantime() {
    return dataTrantime;
  }

  public void setDataTrantime(String dataTrantime) {
    this.dataTrantime = dataTrantime;
  }


  public String getCrttime() {
    return crttime;
  }

  public void setCrttime(String crttime) {
    this.crttime = crttime;
  }


  public String getLmdtime() {
    return lmdtime;
  }

  public void setLmdtime(String lmdtime) {
    this.lmdtime = lmdtime;
  }

}
