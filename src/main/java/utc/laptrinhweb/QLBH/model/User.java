package utc.laptrinhweb.QLBH.model;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.Id;import java.util.Date;@Entity(name = "NHANVIEN")public class User {     @Id     @Column(name = "MA_NV")    private String id;     @Column(name = "TEN")    private String name;    @Column(name = "XA_PHUONG")    private String coutryState;    @Column(name = "QUAN_HUYEN")    private String town;    @Column(name = "TINH_TP")    private String country;    @Column(name = "NGAY_TAO")    private Date createAt;    @Column(name = "SO_DIEN_THOAI")    private String phone;    @Column(name = "NGAY_SINH")    private Date dateOfBirth;    @Column(name = "GIOI_TINH")    private double gender;    @Column(name = "NGAY_SUA")    private Date updateAt;    @Column(name = "TRANG_THAI")    private String state;    @Column(name = "TRANG_THAI")    private String role;}