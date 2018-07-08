package sample.obj;

import java.util.Date;

public class Scan {
    public Scan(Date scantime, double dlrate, double uprate) {
        this.scantime = scantime;
        this.dlrate = dlrate;
        this.uprate = uprate;
    }

    private Date scantime;
    private double dlrate;
    private double uprate;

    public Scan() {
    }

    public Date getScantime() {
        return scantime;
    }

    public void setScantime(Date scantime) {
        this.scantime = scantime;
    }

    public double getDlrate() {
        return dlrate;
    }

    public void setDlrate(double dlrate) {
        this.dlrate = dlrate;
    }

    public double getUprate() {
        return uprate;
    }

    public void setUprate(double uprate) {
        this.uprate = uprate;
    }
}
