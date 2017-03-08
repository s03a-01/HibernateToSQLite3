/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvToSQLite3;

import com.github.mygreen.supercsv.annotation.CsvBean;
import com.github.mygreen.supercsv.annotation.CsvColumn;
import com.github.mygreen.supercsv.annotation.format.CsvDateTimeFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * データベースアクセス用のPOJO class
 * super csv annotation のPOJO はpublic である必要がある
 * 引数無しのpublic なconstructor が必要
 * jpa/eclipseLink のEntity
 * http://mygreen.github.io/super-csv-annotation/sphinx/howtouse.html
 * @author okuda
 */
@Entity
@Table(indexes = @Index(columnList = "marketDate,code"))
@CsvBean( header = false )
public class PojoUntil2005 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique=true,nullable = false) 
    private Long id;
    
    @CsvColumn(number=1)
//    @CsvDateTimeFormat(pattern="uuuu/MM/dd") // LocalDateTime
    @CsvDateTimeFormat(pattern="yyyy/MM/dd") // Date, Calendar
    @Column(nullable = false)
    @Temporal(TemporalType.DATE) // Date 型でない場合は付けないほうがよい？動作しない
    private Date marketDate;        //  日付、yyyy/mm/dd

    @CsvColumn(number=2)
    @Column(nullable = false)
    private int    code;        // 銘柄コード, 4桁, 頭は0 以外

    @CsvColumn(number=3)
    @Column(nullable = false, length = 30)
    private String name;        // 銘柄名

    @CsvColumn(number=4)
    @Column(nullable = false)
    private float  marketOpen;        // 始値

    @CsvColumn(number=5)
    @Column(nullable = false, precision=10, scale=4)
    private float  high;        // 高値
//    private BigDecimal  high;        // 高値

    @CsvColumn(number=6)
    @Column(nullable = false, precision=10, scale=4)
    private float  low;         // 安値

    @CsvColumn(number=7)
    @Column(nullable = false)
    private float  marketClose;       // 終値
    @CsvColumn(number=8)
    @Column(nullable = false)
 
    private long   volume;      // 出来高
    @CsvColumn(number=9)
    @Column(nullable = false)
    private int    market;      // 市場区分, 1: 東証一部 2:東証2部 3: 店頭、マザーズ
    
    public PojoUntil2005 () {
    }
    
    @Override
    public String toString() {
        return "date = " + marketDate + ", code = " + code + ", name = " + name
                + ", open = " + marketOpen + ", high = " + high + ", low = " + low
                + ", close = " + marketClose + ", volume = " + volume
                + ", market = " + market;
    }

    // getter, setter
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

       public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public int getMarket() {
        return market;
    }

    public void setMarket(int market) {
        this.market = market;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMarketDate() {
        return marketDate;
    }

    public void setMarketDate(Date marketDate) {
        this.marketDate = marketDate;
    }

    public float getMarketOpen() {
        return marketOpen;
    }

    public void setMarketOpen(float marketOpen) {
        this.marketOpen = marketOpen;
    }

    public float getMarketClose() {
        return marketClose;
    }

    public void setMarketClose(float marketClose) {
        this.marketClose = marketClose;
    }
    
} // end of class
