create database QuanLyKhachSan
go
--drop database QuanLyKhachSan
use QuanLyKhachSan
go

--Create table
create table DichVu(
	MaDichVu nvarchar(50) not null primary key,
	TenDichVu nvarchar(50),
	Gia float
)
create table Phong(
	MaPhong nvarchar(50) not null primary key,
	TenPhong nvarchar(50),
	LoaiPhong nvarchar(50),
	GiaPhong float,
	MoTa nvarchar(200),
	--Tinh trạng còn trống hay đã đặt phòng
	--0: còn trống
	--1: đã đặt
	TinhTrang bit default 0
)

create table KhachHang(
	MaKhachHang nvarchar(50) not null primary key,
	TenKhachHang nvarchar(50) not null,
	CMT nvarchar(50) not null,
	SDT nvarchar(10) not null,
	Gmail nvarchar(50)
)

create table NhanVien(
	MaNhanVien nvarchar(50) not null primary key,
	HoTen nvarchar(50) not null,
	Pwd nvarchar(50) not null,
	--Chức vụ
	--0: nhân viên bình thường
	--1: quản lý
	ChucVu bit not null,
	--Giới tính
	GioiTinh char(3),
	CMT nvarchar(50) not null,
	SDT char(10) not null,
	Gmail nvarchar(50),
	DiaChi nvarchar(200)
)
create table PhieuDatPhong(
	MaDatPhong nvarchar(50) not null primary key,
	-----FOREIGN KEY
	MaNV nvarchar(50) not null,
	MaPhong nvarchar(50) not null,
	IDNguoiDatPhong nvarchar(50) not null,
	--MaHoaDon nvarchar(50) not null,
	-----
	NgayDatPhong date,
	NgayCheckIn date,
	NgayCheckOut date,
	SoNguoi int,
	GhiChu nvarchar(50)

	CONSTRAINT fk_nv FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNhanVien),
	CONSTRAINT fk_mp FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong),
	CONSTRAINT fk_kh FOREIGN KEY (IDNguoiDatPhong) REFERENCES KhachHang(MaKhachHang),
	--CONSTRAINT fk_hd FOREIGN KEY (MaHoaDon) REFERENCES HoaDonThanhToan(MaHoaDon)
	
)
create table HoaDonDichVuPhong(
	MaDatPhong nvarchar(50) not null,
	MaDichVu nvarchar(50) not null,
	SoLuong int,
	-------------------------
	--Thành tiền dịch vụ = SUM(Gia*SoLuong)
	--Giá(DichVu)
	--SoLuong(HoaDonDichVuPhong)
	ThanhTienDichVu float,--Dynamic
	-------------------------
	CONSTRAINT fk_maDP FOREIGN KEY (MaDatPhong) REFERENCES PhieuDatPhong(MaDatPhong),
	CONSTRAINT fk_maDV FOREIGN KEY (MaDichVu) REFERENCES DichVu(MaDichVu)
)

create table HoaDonThanhToan(
	MaHoaDon nvarchar(50) not null primary key,
	NgayThanhToan date,
	--Hình thức thanh toán
	--0: tiền mặt
	--1: chuyển khoản
	HinhThucThanhToan bit,

	---------------------------------------------
	--Thành tiền phòng = số ngày ở(PhieuDatPhong) * giá phòng(Phong)
	ThanhTienPhong float,--Dynamic
	--Tổng thanh toán = SUM(ThanhTienDichVuPhong) (HoaDonDichVuPhong) + ThanhTienPhong
	TongThanhToan float,--Dynamic
	---------------------------------------------
	GhiChu nvarchar(200),

	CONSTRAINT fk_hd FOREIGN KEY (MaHoaDon) REFERENCES PhieuDatPhong(MaDatPhong)
)


--INSERT DATA
--Khach Hang
select * from KhachHang
INSERT INTO KhachHang values(N'KH001',N'Nguyễn Đức Vương','12345678910','0799558911','vuongnguyen221203@gmail.com')
INSERT INTO KhachHang values(N'KH002',N'Nguyễn Thu Sương ','11125367881','0258104791','thusuong120402@gmail.com')
INSERT INTO KhachHang values(N'KH003',N'Nguyễn Đức Nhật','17655899012','0491228491','nhatnguyen121103@gmail.com')
INSERT INTO KhachHang values(N'KH004',N'Trần Lê Quốc Bình','13456788990','0309445891','quocbinh230603@gmail.com')
INSERT INTO KhachHang values(N'KH005',N'Nguyễn Trần Hải Đăng','12344465879','0907021740','haidang080703@gmail.com')
--INSERT INTO KhachHang values(N'KH006',N'Lê Thu Thủy','11143567781','0760912287','thuthuy300401@gmail.com')


--Nhan Vien
select * from NhanVien
INSERT INTO NhanVien values(N'NV001', N'Nguyễn Trà My', 'tramy6665', 0, 'Nữ', 12345678801, 0967344087,
'tramy1234@gmail.com', 'Tinh Tien Giang')
INSERT INTO NhanVien values(N'NV002', N'Nguyễn Huỳnh Bảo', 'huybao2345', 1, 'Nam', 13598800021, 0134556780,
'huynhbao4444@gmail.com', 'Tinh Tay Ninh')
INSERT INTO NhanVien values(N'NV003', N'Lê Trần Thu Hà', 'thuha5699', 1, 'Nữ', 10445689901, 0860012345,
'thuha5666@gmail.com', 'Tinh An Giang')
INSERT INTO NhanVien values(N'NV004', N'Phan Đại Nam', 'namphan0123', 0, 'Nam', 14577770124, 0497601223,
'dainam1111@gmail.com', 'Tinh Ca Mau')
INSERT INTO NhanVien values(N'NV005', N'Trần Thanh Thúy', 'thuytran2222', 0, 'Nữ', 14378889012, 0403567790,
'thanhthuy5666@gmail.com', 'Tinh Binh Thuan')
--INSERT INTO NhanVien values(N'NV006', N'Trần Công Toàn', 'congtoan0000', 1, 'Nam', 17502254891, 0398865341,
--'congtoan0000@gmail.com', 'Thanh Pho Ho Chi Minh')



-- InsertData Phong
--MaPhong nvarchar(50) not null primary key,
--	TenPhong nvarchar(50),
--	LoaiPhong nvarchar(50),
--	GiaPhong float,
--	MoTa nvarchar(200),
--	Tinh trạng còn trống hay đã đặt phòng
--	0: còn trống
--	1: đã đặt
--	TinhTrang bit default 0
INSERT INTO Phong values(N'P001',N'A001',N'VIP0001',10000000,N'Phòng có view biển',0)
INSERT INTO Phong values(N'P002',N'A002',N'Single0001',10000000,N'Phòng đơn',0)
INSERT INTO Phong values(N'P003',N'A003',N'Double0001',10000000,N'Phòng giành cho cặp đôi',0)
INSERT INTO Phong values(N'P004',N'A004',N'Triple0001',10000000,N'Phòng giành cho bạn bè',0)
INSERT INTO Phong values(N'P005',N'A005',N'VIP0001',10000000,N'Phòng có view biển',0)
--DELETE from Phong where MaPhong = 'P001'

go
--select *from Phong
go

-- InsertData DichVu
--select *from DichVu
-----InsertData DichVu
INSERT INTO DichVu values(N'DV001',N'Khăn giấy',3000)
INSERT INTO DichVu values(N'DV002',N'Nước ngọt 7 Up 330ml',10000)
INSERT INTO DichVu values(N'DV003',N'Khăn ướt',4000)
INSERT INTO DichVu values(N'DV004',N'Nước suối Aquafina 500ml',3000)
INSERT INTO DichVu values(N'DV005',N'Coca cola',8000)

--------------------------------HOA DON DICH VU PHONG-----------------------------------------------
--create trigger insert
go
CREATE TRIGGER trigger_hddvphong on HoaDonDichVuPhong
after insert
as 
	--khai báo biến giá và mã dv
	declare @gia float, @madv varchar(50), @maDP varchar(50)
	--lấy dữ liệu từ insert
	select @madv = MaDichVu, @maDP = MaDatPhong
	from inserted 
	--lấy dữ liệu từ bảng Dịch vụ
	select @gia = Gia 
	from DichVu
	where MaDichVu like @madv
	--Thực hiện update tổng từng dịch vụ theo từng mã đặt phòng
	UPDATE HoaDonDichVuPhong
	set ThanhTienDichVu = @gia * SoLuong
	where MaDichVu = @madv and MaDatPhong = @maDP
go

----Phieu Dat Phong-----
INSERT INTO PhieuDatPhong (MaDatPhong,MaNV, MaPhong, IDNguoiDatPhong, NgayDatPhong, NgayCheckIn, NgayCheckOut, SoNguoi, GhiChu) 
values(N'PDPD001',N'NV001',N'P001', N'KH001', '2023/3/16', '2023/3/18', '2023/3/22',2,''),
(N'PDPD003',N'NV002',N'P003', N'KH003','2023/3/29','2023/4/9', '2023/4/12',2,''),
(N'PDPD004',N'NV002',N'P004', N'KH004','2023/4/4','2023/4/6', '2023/4/12',2,''),
(N'PDPD002',N'NV001',N'P002', N'KH002','2023/3/27','2023/3/28', '2023/4/2',2,''),
(N'PDPD005',N'NV003',N'P001', N'KH005','2023/4/25','2023/4/25', '2023/4/28',1,'')
--select * from PhieuDatPhong
------------------------------------
--INSERT INTO Phong (MaPhong) values(N'P001')
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD001',N'DV001',9)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD001',N'DV003',5)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD001',N'DV004',7)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD001',N'DV005',3)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD002',N'DV001',5)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD002',N'DV004',15)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD003',N'DV002',10)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD003',N'DV005',4)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD004',N'DV004',8)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD004',N'DV005',20)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD005',N'DV003',10)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDPD005',N'DV004',6)

--select MaDatPhong,dvp.MaDichVu,SoLuong,dv.Gia,ThanhTienDichVu from HoaDonDichVuPhong dvp join DichVu dv on dvp.MaDichVu = dv.MaDichVu
--UPDATE HoaDonDichVuPhong Set SoLuong = 3 where MaPhong = 'P001' and MaDichVu =  'DV003'
--select * from HoaDonDichVuPhong
--DELETE from HoaDonDichVuPhong where MaPhong = ? and MaDichVu = ?

--select * from DichVu
--declare @gia float
--select @gia= Gia from DichVu where MaDichVu = 'DV001'
--print(@gia)

--select MaPhong,dvp.MaDichVu,SoLuong,dv.Gia,ThanhTienDichVu 
--from HoaDonDichVuPhong dvp join DichVu dv on dvp.MaDichVu = dv.MaDichVu


--declare @gia float
--select @gia=Gia from DichVu where MaDichVu =  'DV001'
--print(@gia)

--select Gia from DichVu where MaDichVu = 'DV001'

--UPDATE HoaDonDichVuPhong
--Set SoLuong = 7
--where MaPhong = 'P001' and MaDichVu = 'DV001'

--UPDATE HoaDonDichVuPhong
--Set ThanhTienDichVu = SoLuong * @gia
--where MaPhong = 'P001' and MaDichVu = 'DV003'

--------------------------------------------------------------------------



INSERT INTO HoaDonThanhToan (MaHoaDon,NgayThanhToan, HinhThucThanhToan, ThanhTienPhong, TongThanhToan, GhiChu) 
values(N'PDPD001','2023/3/22',1, 2000000, 2030000, ''),
(N'PDPD002','2023/4/2',0, 3000000, 3050000, ''),
(N'PDPD003','2023/4/12',0, 1000000, 1020000, ''),
(N'PDPD004','2023/4/12',1, 3000000, 3050000, ''),
(N'PDPD005','2023/4/28',1, 1000000, 1060000, '')

--delete from HoaDonThanhToan where MaHoaDon = 'HD001'
--select * from HoaDonThanhToan