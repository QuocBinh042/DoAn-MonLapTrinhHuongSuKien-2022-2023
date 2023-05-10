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
	GioiTinh nvarchar(3),
	CMT nvarchar(50) not null,
	SDT nvarchar(10) not null,
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
	MaDatPhong nvarchar(50) not null,
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

	CONSTRAINT fk_dp FOREIGN KEY (MaDatPhong) REFERENCES PhieuDatPhong(MaDatPhong)
)

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

--------------------------------KHACH HANG-----------------------------------------------
--Insert Data
INSERT INTO KhachHang values(N'KH001',N'Nguyễn Đức Vương','12345678910','0799558911','vuongnguyen221203@gmail.com') 
INSERT INTO KhachHang values(N'KH002',N'Nguyễn Thu Sương ','11125367881','0258104791','thusuong120402@gmail.com')
INSERT INTO KhachHang values(N'KH003',N'Nguyễn Đức Nhật','17655899012','0491228491','nhatnguyen121103@gmail.com')
INSERT INTO KhachHang values(N'KH004',N'Trần Lê Quốc Bình','13456788990','0309445891','quocbinh230603@gmail.com')
INSERT INTO KhachHang values(N'KH005',N'Nguyễn Trần Hải Đăng','12344465879','0907021740','haidang080703@gmail.com')
INSERT INTO KhachHang values(N'KH006',N'Lê Thu Thủy','11143567781','0760912287','thuthuy300401@gmail.com')
INSERT INTO KhachHang values(N'KH007',N'Nguyễn Văn Phương','16850444871','0773870124','vanphuong111112@gmail.com')
INSERT INTO KhachHang values(N'KH008',N'Nguyễn Thanh Thanh','14879640124','0375911450','thanhthanh549543@gmail.com')
INSERT INTO KhachHang values(N'KH009',N'Nguyễn Bùi Phát Đạt','16928655551','0298124508','phatdat080703@gmail.com')
INSERT INTO KhachHang values(N'KH010',N'Châu Đăng Khoa','14659801870','0569126580','dangkhoa149233@gmail.com')
INSERT INTO KhachHang values(N'KH011',N'Ngô Vĩnh Phúc','13195679011','0297333187','ngophuc438121@gmail.com')
INSERT INTO KhachHang values(N'KH012',N'Dương Đình Lưu','12952605492','0327890123','dinhluu121104@gmail.com')
INSERT INTO KhachHang values(N'KH013',N'Nguyễn Anh Khoa','14971234076','0819345021','anhkhoa012367@gmail.com')
INSERT INTO KhachHang values(N'KH014',N'Trần Lê Phương Khánh','13596120791','0196298071','phuongkhanh219604@gmail.com')
INSERT INTO KhachHang values(N'KH015',N'Nguyễn Minh Nhựt','16920123336','0495012397','minhnhut549123@gmail.com')
--Xem toan bo khach hang
select * from KhachHang

--------------------------------NHAN VIEN-----------------------------------------------
--InsertData
INSERT INTO NhanVien values(N'NV001', N'Nguyễn Trà My', 'tramy6665', 0, N'Nữ', 12345678801, 0967344087,
N'tramy1234@gmail.com', N'Tỉnh Tiền Giang')
INSERT INTO NhanVien values(N'NV002', N'Nguyễn Huỳnh Bảo', 'huybao2345', 1, N'Nam', 13598800021, 0134556780,
N'huynhbao4444@gmail.com', N'Tỉnh Tây Ninh')
INSERT INTO NhanVien values(N'NV003', N'Lê Trần Thu Hà', 'thuha5699', 1, N'Nữ', 10445689901, 0860012345,
'thuha5666@gmail.com', N'Tỉnh An Giang')
INSERT INTO NhanVien values(N'NV004', N'Phan Đại Nam', 'namphan0123', 0, N'Nam', 14577770124, 0497601223,
'dainam1111@gmail.com', N'Tỉnh Cà Mau')
INSERT INTO NhanVien values(N'NV005', N'Trần Thanh Thúy', 'thuytran2222', 0, N'Nữ', 14378889012, 0403567790,
'thanhthuy5666@gmail.com', N'Tỉnh Bình Thuận')
INSERT INTO NhanVien values(N'NV006', N'Trần Công Toàn', '123456', 1, N'Nam', 17502254891, 0398865341,
'congtoan0000@gmail.com', N'Thành Phố Hồ Chí Minh')
INSERT INTO NhanVien values(N'NV007', N'Nguyễn Công Vinh', '123456', 1, N'Nam', 14659801870, 0495012397,
'nguyenvinh1234@gmail.com', N'Tỉnh Bạc Liêu')
INSERT INTO NhanVien values(N'NV008', N'Phạm Thị Thanh Vy', '123456', 0, N'Nữ', 12962007961, 0329276801,
'thanhvy6492@gmail.com', N'Tỉnh Tiền Giang')
INSERT INTO NhanVien values(N'NV009', N'Ngô Thị Kim Châu', '123456', 1, N'Nữ', 13456788990, 0296012578,
'kimchau7777@gmail.com', N'Tỉnh Ninh Thuận')
INSERT INTO NhanVien values(N'NV010', N'Phạm Tấn Đạt', '123456', 0, N'Nam', 14659801870, 0569207123,
'tandat6032@gmail.com', N'Thành Phố Hà Nội')
INSERT INTO NhanVien values(N'NV011', N'Trần Nam Phương', '123456', 1, N'Nam', 13951065912, 0879206591,
'namphuong5931@gmail.com', N'Tỉnh Châu Đốc')
INSERT INTO NhanVien values(N'NV012', N'Lê Thanh Thủy', '123456', 1, N'Nữ', 15914057801, 0901456891,
'thanhthuy2482@gmail.com', N'Thành Phố Hồ Chí Minh')
INSERT INTO NhanVien values(N'NV013', N'Trần Lê Đại Trí', '123456', 0, N'Nam', 18914034871, 0491547801,
'daitri6291@gmail.com', N'Tỉnh Yên Bái')
INSERT INTO NhanVien values(N'NV014', N'Dương Đăng Khoa', '123456', 1, N'Nam', 15195548991, 0158386012,
'dangkhoa0247@gmail.com', N'Tỉnh Hà Nam')
INSERT INTO NhanVien values(N'NV015', N'Trần Thị Thảo My', '123456', 0, N'Nữ', 14817507912, 0681497001,
'thaomy5291@gmail.com', N'Thành Phố Hà Nội')
--Xem toan bo NhanVien
select * from NhanVien

--------------------------------PHONG-----------------------------------------------
--InsertData
INSERT INTO Phong values(N'P001',N'A001',N'VIP0001',10000000,N'Phòng có view biển',0)
INSERT INTO Phong values(N'P002',N'A002',N'Single0001',10000000,N'Phòng đơn',0)
INSERT INTO Phong values(N'P003',N'A003',N'Double0001',10000000,N'Phòng giành cho cặp đôi',0)
INSERT INTO Phong values(N'P004',N'A004',N'Triple0001',10000000,N'Phòng giành cho bạn bè',0)
INSERT INTO Phong values(N'P005',N'A005',N'VIP0001',10000000,N'Phòng có view biển',0)
INSERT INTO Phong values(N'P006',N'A006',N'VIP0002',90000000,N'Phòng có view biển',0)
INSERT INTO Phong values(N'P007',N'A007',N'Single0002',10000000,N'Phòng đơn',0)
INSERT INTO Phong values(N'P008',N'A008',N'Double0002',50000000,N'Phòng giành cho cặp đôi',0)
INSERT INTO Phong values(N'P009',N'A009',N'Triple0002',50000000,N'Phòng giành cho bạn bè',0)
INSERT INTO Phong values(N'P010',N'A010',N'VIP0002',70000000,N'Phòng có view biển',0)
INSERT INTO Phong values(N'P011',N'A011',N'VIP0002',40000000,N'Phòng có view biển',0)
INSERT INTO Phong values(N'P012',N'A012',N'Single0002',10000000,N'Phòng đơn',0)
INSERT INTO Phong values(N'P013',N'A013',N'Double0002',80000000,N'Phòng giành cho cặp đôi',0)
INSERT INTO Phong values(N'P014',N'A014',N'Triple0002',90000000,N'Phòng giành cho bạn bè',0)
INSERT INTO Phong values(N'P015',N'A015',N'VIP0002',50000000,N'Phòng có view biển',0)
go
--Xoa phong theo MaPhong
--DELETE from Phong where MaPhong = 'P001'
go
--Xem toan bo Phong
select *from Phong
go

--------------------------------DICH VU-----------------------------------------------
--InsertData
INSERT INTO DichVu values(N'DV001',N'Khăn giấy',3000)
INSERT INTO DichVu values(N'DV002',N'Nước ngọt 7 Up 330ml',10000)
INSERT INTO DichVu values(N'DV003',N'Khăn ướt',4000)
INSERT INTO DichVu values(N'DV004',N'Nước suối Aquafina 500ml',3000)
INSERT INTO DichVu values(N'DV005',N'Coca cola',8000)
INSERT INTO DichVu values(N'DV006',N'Nước suối Lavie 500ml',5000)
INSERT INTO DichVu values(N'DV007',N'Nước ngọt 7 Up 330ml',10000)
INSERT INTO DichVu values(N'DV008',N'Pepsi zero zalo 350ml',10000)
INSERT INTO DichVu values(N'DV009',N'Nước suối Aquafina 500ml',5000)
INSERT INTO DichVu values(N'DV010',N'Khăn ướt',8000)
INSERT INTO DichVu values(N'DV011',N'Khăn giấy',8000)
INSERT INTO DichVu values(N'DV012',N'Nước ngọt 7 Up 330ml',10000)
INSERT INTO DichVu values(N'DV013',N'Khăn ướt',10000)
INSERT INTO DichVu values(N'DV014',N'Nước suối Aquafina 500ml',20000)
INSERT INTO DichVu values(N'DV015',N'Coca cola',500000)
--Xem Toan bo DichVu
select *from DichVu

--------------------------------PhieuDatPhong-----------------------------------------------
--InsertData
INSERT INTO PhieuDatPhong (MaDatPhong,MaNV, MaPhong, IDNguoiDatPhong, NgayDatPhong, NgayCheckIn, NgayCheckOut, SoNguoi, GhiChu) 
values(N'PDP001',N'NV001',N'P001', N'KH001', '2023/5/11', '2023/5/12', '2023/5/22',2,''),
	  (N'PDP002',N'NV002',N'P002', N'KH002','2023/5/12','2023/5/12', '2023/5/17',6,''),
	  (N'PDP003',N'NV003',N'P003', N'KH003','2023/5/14','2023/5/15', '2023/5/20',3,''),
	  (N'PDP004',N'NV004',N'P004', N'KH004','2023/5/15','2023/5/16', '2023/5/23',4,''),
	  (N'PDP005',N'NV005',N'P005', N'KH005','2023/5/25','2023/5/26', '2023/5/28',1,''),
	  (N'PDP006',N'NV006',N'P006', N'KH006','2023/6/11','2023/6/12', '2023/6/22',2,''),
	  (N'PDP007',N'NV007',N'P007', N'KH007','2023/6/12','2023/6/12', '2023/6/17',3,''),
	  (N'PDP008',N'NV008',N'P008', N'KH008','2023/6/14','2023/6/15', '2023/6/20',3,''),
	  (N'PDP009',N'NV009',N'P009', N'KH009','2023/6/15','2023/6/16', '2023/6/23',4,''),
	  (N'PDP010',N'NV010',N'P010', N'KH010','2023/6/25','2023/6/26', '2023/6/28',1,''),
	  (N'PDP011',N'NV011',N'P011', N'KH011','2023/7/11','2023/7/12', '2023/7/22',2,''),
	  (N'PDP012',N'NV012',N'P012', N'KH012','2023/7/12','2023/7/12', '2023/7/17',5,''),
	  (N'PDP013',N'NV013',N'P013', N'KH013','2023/7/14','2023/7/15', '2023/7/20',3,''),
	  (N'PDP014',N'NV014',N'P014', N'KH014','2023/7/15','2023/7/16', '2023/7/23',4,''),
	  (N'PDP015',N'NV015',N'P015', N'KH015','2023/7/25','2023/7/26', '2023/7/28',1,'')
--Xem toan bo PhieuDatPhong
select * from PhieuDatPhong


--------------------------------HoaDonDichVuPhong-----------------------------------------------
--InsertData HoaDonDichVuPhong
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP001',N'DV001',9)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP002',N'DV002',5)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP003',N'DV003',7)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP004',N'DV004',3)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP005',N'DV005',5)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP006',N'DV006',15)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP007',N'DV007',10)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP008',N'DV008',4)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP009',N'DV009',8)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP010',N'DV010',20)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP011',N'DV011',10)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP012',N'DV012',6)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP013',N'DV013',20)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP014',N'DV014',10)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP005',N'DV015',6)
--Xem toan bo HoaDonDichVuPhong
select * from HoaDonDichVuPhong

--select ThanhTien = SUM(ThanhTienDichVu)
--from HoaDonDichVuPhong
--where MaDatPhong = 'PDP005' 
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

--------------------------------HoaDonThanhToan-----------------------------------------------
--InsertData HoaDonThanhToan
INSERT INTO HoaDonThanhToan (MaHoaDon, MaDatPhong, NgayThanhToan, HinhThucThanhToan, ThanhTienPhong, TongThanhToan, GhiChu) 
values(N'HD001',N'PDP001','2023/5/2',1, 2000000, 2030000, ''),
	  (N'HD002',N'PDP002','2023/5/4',0, 3000000, 3050000, ''),
	  (N'HD003',N'PDP003','2023/5/12',0, 1000000, 1020000, ''),
	  (N'HD004',N'PDP004','2023/5/18',1, 3000000, 3050000, ''),
	  (N'HD005',N'PDP005','2023/5/28',1, 1000000, 1060000, ''),
	  (N'HD006',N'PDP006','2023/6/4',1, 2000000, 2030000, ''),
	  (N'HD007',N'PDP007','2023/6/8',0, 3000000, 3050000, ''),
	  (N'HD008',N'PDP008','2023/6/12',0, 1000000, 1020000, ''),
	  (N'HD009',N'PDP009','2023/6/16',1, 3000000, 3050000, ''),
	  (N'HD010',N'PDP010','2023/6/20',1, 1000000, 1060000, ''),
	  (N'HD011',N'PDP011','2023/7/5',1, 2000000, 2030000, ''),
	  (N'HD012',N'PDP012','2023/7/10',0, 3000000, 3050000, ''),
	  (N'HD013',N'PDP013','2023/7/15',0, 1000000, 1020000, ''),
	  (N'HD014',N'PDP014','2023/7/20',1, 3000000, 3050000, ''),
	  (N'HD015',N'PDP015','2023/7/25',1, 1000000, 1060000, '')
		--Xem toan bo HoaDonThanhToan
select * from HoaDonThanhToan
--Xoa HoaDonThanhToan khi biet MaHoaDon
--delete from HoaDonThanhToan where MaHoaDon = 'HD001'
