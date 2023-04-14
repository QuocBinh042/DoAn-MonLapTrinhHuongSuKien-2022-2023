create database QuanLyKhachSan
go
use QuanLyKhachSan
go
create table DichVu(
	MaDV nvarchar(50) not null primary key,
	TenDV nvarchar(50),
	Gia float
)
create table Phong(
	MaPhong nvarchar(50) not null primary key,
	TenPhong nvarchar(50),
	LoaiPhong nvarchar(50),
	GiaPhong float,
	MoTa nvarchar(200),
	TinhTrang bit
)

create table KhachHang(
	MaKhachHang nvarchar(50) not null primary key,
	TenKhachHang nvarchar(50),
	CMT nvarchar(50),
	SDT nvarchar(10),
	Gmail nvarchar(50)
)

create table NhanVien(
	MaNhanVien nvarchar(50) not null primary key,
	HoTen nvarchar(50),
	CMT nvarchar(50),
	SDT nvarchar(10),
	Gmail nvarchar(50),
	ChucVu nvarchar(50)
)

create table HoaDonThanhToan(
	MaHoaDon nvarchar(50) not null primary key,
	NgayThanhToan datetime,
	HinhThucThanhToan nvarchar(50),
	TongThanhToan float,
	GhiChu nvarchar(200)
)

create table PhieuDatPhong(
	MaDatPhong nvarchar(50) not null primary key,
	NgayDatPhong date,
	NgayDen date,
	NgayDi date,
	SoNguoi int,
	GiaTien float
)