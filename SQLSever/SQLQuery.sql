create database QuanLyKhachSan
go
drop database QuanLyKhachSan
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
--MaKhachHang--TenKhachHang--ChungMinhThu--SoDienThoai--Gmail
--Insert Data
INSERT INTO KhachHang values(N'KH001',N'Nguyễn Đức Vương','123456789104','0799558911','vuongnguyen221203@gmail.com') 
INSERT INTO KhachHang values(N'KH002',N'Nguyễn Thu Sương ','111253678815','0258104791','thusuong120402@gmail.com')
INSERT INTO KhachHang values(N'KH003',N'Nguyễn Đức Nhật','176558990128','0491228491','nhatnguyen121103@gmail.com')
INSERT INTO KhachHang values(N'KH004',N'Trần Lê Quốc Bình','134567889903','0309445891','quocbinh230603@gmail.com')
INSERT INTO KhachHang values(N'KH005',N'Nguyễn Trần Hải Đăng','123444658790','0907021740','haidang080703@gmail.com')
INSERT INTO KhachHang values(N'KH006',N'Lê Thu Thủy','111435677816','0760912287','thuthuy300401@gmail.com')
INSERT INTO KhachHang values(N'KH007',N'Nguyễn Văn Phương','168504448712','0773870124','vanphuong111112@gmail.com')
INSERT INTO KhachHang values(N'KH008',N'Nguyễn Thanh Thanh','148796401243','0375911450','thanhthanh549543@gmail.com')
INSERT INTO KhachHang values(N'KH009',N'Nguyễn Bùi Phát Đạt','169286555514','0298124508','phatdat080703@gmail.com')
INSERT INTO KhachHang values(N'KH010',N'Châu Đăng Khoa','146598018703','0569126580','dangkhoa149233@gmail.com')
INSERT INTO KhachHang values(N'KH011',N'Ngô Vĩnh Phúc','131956790112','0297333187','ngophuc438121@gmail.com')
INSERT INTO KhachHang values(N'KH012',N'Dương Đình Lưu','129526054923','0327890123','dinhluu121104@gmail.com')
INSERT INTO KhachHang values(N'KH013',N'Nguyễn Anh Khoa','149712340768','0819345021','anhkhoa012367@gmail.com')
INSERT INTO KhachHang values(N'KH014',N'Trần Lê Phương Khánh','135961207912','0196298071','phuongkhanh219604@gmail.com')
INSERT INTO KhachHang values(N'KH015',N'Nguyễn Minh Nhựt','169201233367','0495012397','minhnhut549123@gmail.com')
--Xem toan bo khach hang
select * from KhachHang

--------------------------------NHAN VIEN-----------------------------------------------
--InsertData
INSERT INTO NhanVien values(N'NV001', N'Nguyễn Trà My', 'tramy6665', 0, N'Nữ', 123456788012, '0967344087',
N'tramy1234@gmail.com', N'Tỉnh Tiền Giang')
INSERT INTO NhanVien values(N'NV002', N'Nguyễn Huỳnh Bảo', 'huybao2345', 1, N'Nam', 135988000213, '0134556780',
N'huynhbao4444@gmail.com', N'Tỉnh Tây Ninh')
INSERT INTO NhanVien values(N'NV003', N'Lê Trần Thu Hà', 'thuha5699', 1, N'Nữ', 104456899012, '0860012345',
'thuha5666@gmail.com', N'Tỉnh An Giang')
INSERT INTO NhanVien values(N'NV004', N'Phan Đại Nam', 'namphan0123', 0, N'Nam', 145777701243, '0497601223',
'dainam1111@gmail.com', N'Tỉnh Cà Mau')
INSERT INTO NhanVien values(N'NV005', N'Trần Thanh Thúy', 'thuytran2222', 0, N'Nữ', 143788890123, '0403567790',
'thanhthuy5666@gmail.com', N'Tỉnh Bình Thuận')
INSERT INTO NhanVien values(N'NV006', N'Trần Công Toàn', '123456', 1, N'Nam', 175022548912, '0398865341',
'congtoan0000@gmail.com', N'Thành Phố Hồ Chí Minh')
INSERT INTO NhanVien values(N'NV007', N'Nguyễn Công Vinh', '123456', 1, N'Nam', 146598018703, '0495012397',
'nguyenvinh1234@gmail.com', N'Tỉnh Bạc Liêu')
INSERT INTO NhanVien values(N'NV008', N'Phạm Thị Thanh Vy', '123456', 0, N'Nữ', 129620079613, '0329276801',
'thanhvy6492@gmail.com', N'Tỉnh Tiền Giang')
INSERT INTO NhanVien values(N'NV009', N'Ngô Thị Kim Châu', '123456', 1, N'Nữ', 134567889901, '0296012578',
'kimchau7777@gmail.com', N'Tỉnh Ninh Thuận')
INSERT INTO NhanVien values(N'NV010', N'Phạm Tấn Đạt', '123456', 0, N'Nam', 146598018703, '0569207123',
'tandat6032@gmail.com', N'Thành Phố Hà Nội')
INSERT INTO NhanVien values(N'NV011', N'Trần Nam Phương', '123456', 1, N'Nam', 139510659123, '0879206591',
'namphuong5931@gmail.com', N'Tỉnh Châu Đốc')
INSERT INTO NhanVien values(N'NV012', N'Lê Thanh Thủy', '123456', 1, N'Nữ', 159140578013, '0901456891',
'thanhthuy2482@gmail.com', N'Thành Phố Hồ Chí Minh')
INSERT INTO NhanVien values(N'NV013', N'Trần Lê Đại Trí', '123456', 0, N'Nam', 189140348713, '0491547801',
'daitri6291@gmail.com', N'Tỉnh Yên Bái')
INSERT INTO NhanVien values(N'NV014', N'Dương Đăng Khoa', '123456', 1, N'Nam', 151955489912, '0158386012',
'dangkhoa0247@gmail.com', N'Tỉnh Hà Nam')
INSERT INTO NhanVien values(N'NV015', N'Trần Thị Thảo My', '123456', 0, N'Nữ', 148175079123, '0681497001',
'thaomy5291@gmail.com', N'Tỉnh Tiền Giang')
--Xem toan bo NhanVien
select * from NhanVien

--------------------------------PHONG-----------------------------------------------
--MaPhong--TenPhong--LoaiPhong--GiaPhong--MoTa--TinhTrang
--InsertData
INSERT INTO Phong values(N'P001',N'C001',N'SINGLE',100000,N'Phòng đơn',0)
INSERT INTO Phong values(N'P002',N'C002',N'SINGLE',100000,N'Phòng đơn',0)
INSERT INTO Phong values(N'P003',N'C003',N'SINGLE',100000,N'Phòng đơn',0)
INSERT INTO Phong values(N'P004',N'C004',N'SINGLE',500000,N'Phòng đơn có view biển',0)
INSERT INTO Phong values(N'P005',N'C005',N'SINGLE',500000,N'Phòng đơn có view biển',0)
INSERT INTO Phong values(N'P006',N'B001',N'DOUBLE',200000,N'Phòng giành cho cặp đôi',0)
INSERT INTO Phong values(N'P007',N'B002',N'DOUBLE',200000,N'Phòng giành cho cặp đôi',0)
INSERT INTO Phong values(N'P008',N'B003',N'DOUBLE',200000,N'Phòng giành cho cặp đôi',0)
INSERT INTO Phong values(N'P009',N'B004',N'DOUBLE',750000,N'Phòng giành cho cặp đôi có view biển',0)
INSERT INTO Phong values(N'P010',N'B005',N'DOUBLE',750000,N'Phòng giành cho cặp đôi có view biển',0)
INSERT INTO Phong values(N'P011',N'A001',N'VIP',1000000,N'Phòng có view biển',0)
INSERT INTO Phong values(N'P012',N'A002',N'VIP',1500000,N'Phòng có view biển, núi non',0)
INSERT INTO Phong values(N'P013',N'A003',N'VIP',700000,N'Phòng có view núi non',0)
INSERT INTO Phong values(N'P014',N'A004',N'VIP',1200000,N'Phòng có view thành phố',0)
INSERT INTO Phong values(N'P015',N'A005',N'VIP',1000000,N'Phòng có view biển',0)
go

--Update Phong
--UPDATE Phong set TinhTrang = 1 where MaPhong = N'P011'
--UPDATE Phong set TinhTrang = 1 where MaPhong = N'P005'
--UPDATE Phong set TinhTrang = 1 where MaPhong = N'P014'
--UPDATE Phong set TinhTrang = 1 where MaPhong = N'P012'
--UPDATE Phong set TinhTrang = 1 where MaPhong = N'P003'
--UPDATE Phong set TinhTrang = 1 where MaPhong = N'P015'
--
--Update Phong set TenPhong = N'A014', LoaiPhong = N'VIP0006', GiaPhong = 550000, MoTa = N'Phòng đôi view biển và thành phố', TinhTrang = 1
--where MaPhong = 'P014'
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
INSERT INTO DichVu values(N'DV002',N'7 Up',10000)
INSERT INTO DichVu values(N'DV003',N'Khăn ướt',4000)
INSERT INTO DichVu values(N'DV004',N'Nước suối Aquafina 500ml',3000)
INSERT INTO DichVu values(N'DV005',N'Coca cola',8000)
INSERT INTO DichVu values(N'DV006',N'Nước suối Lavie 500ml',5000)
INSERT INTO DichVu values(N'DV007',N'Pepsi',12000)
INSERT INTO DichVu values(N'DV008',N'Pepsi zero calo 350ml',12000)
INSERT INTO DichVu values(N'DV009',N'Nước suối Vĩnh Hão',5000)
INSERT INTO DichVu values(N'DV010',N'Cà phê đá',15000)
INSERT INTO DichVu values(N'DV011',N'Cà phê sữa đá',18000)
INSERT INTO DichVu values(N'DV012',N'Espresso',35000)
INSERT INTO DichVu values(N'DV013',N'Americano',39000)
INSERT INTO DichVu values(N'DV014',N'Cappuccino',55000)
INSERT INTO DichVu values(N'DV015',N'Latte',55000)
--Xem Toan bo DichVu
select *from DichVu

--------------------------------PhieuDatPhong-----------------------------------------------
--MaDatPhong--MaNV-MaPhong--IDNguoiDatPhong--NgayDatPhong--NgayCheckIn-NGayCheckOut-Songuoi-Ghichu
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
--Phần test phòng trùng
--INSERT INTO PhieuDatPhong (MaDatPhong,MaNV, MaPhong, IDNguoiDatPhong, NgayDatPhong, NgayCheckIn, NgayCheckOut, SoNguoi, GhiChu) 
--values(N'PDP016',N'NV006',N'P006', N'KH001','2023/6/12','2023/6/23', '2023/6/25',2,''),
--	  (N'PDP017',N'NV003',N'P001', N'KH011', '2023/5/15', '2023/5/24', '2023/5/28',3,''),
--	  (N'PDP018',N'NV003',N'P005', N'KH014','2023/5/20','2023/5/25', '2023/5/30',1,'')
--Xem toan bo PhieuDatPhong
select * from PhieuDatPhong

--Loc du lieu tu 3 table PhieuDatPhong,Phong,KhachHang
--Danh Sach CheckOut
--select pdp.MaDatPhong,kh.TenKhachHang,p.TenPhong,p.TinhTrang,pdp.NgayCheckIn,pdp.NgayCheckOut,pdp.GhiChu from PhieuDatPhong pdp
--join Phong p on pdp.MaPhong = p.MaPhong
--join KhachHang kh on kh.MaKhachHang = pdp.IDNguoiDatPhong
--where p.TinhTrang = 1 and pdp.NgayCheckOut > pdp.NgayCheckIn;
----Danh Sach CheckIn
--select pdp.MaDatPhong,kh.TenKhachHang,p.TenPhong,p.TinhTrang,pdp.NgayCheckIn,pdp.NgayCheckOut,pdp.GhiChu from PhieuDatPhong pdp
--join Phong p on pdp.MaPhong = p.MaPhong
--join KhachHang kh on kh.MaKhachHang = pdp.IDNguoiDatPhong
--where pdp.NgayCheckIn >= GETDATE() and p.TinhTrang = 0;

--------------------------------HoaDonDichVuPhong-----------------------------------------------
--InsertData HoaDonDichVuPhong
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP001',N'DV001',9)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP001',N'DV002',5)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP001',N'DV003',7)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP004',N'DV004',3)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP004',N'DV005',5)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP006',N'DV006',15)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP007',N'DV007',10)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP007',N'DV008',4)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP007',N'DV009',8)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP007',N'DV010',20)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP007',N'DV011',10)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP014',N'DV012',6)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP014',N'DV013',20)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP014',N'DV014',10)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP005',N'DV015',6)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP016',N'DV002',6)
--Xem toan bo HoaDonDichVuPhong
select * from HoaDonDichVuPhong
--Xử lý form thống kê
--select MaDatPhong,dvp.MaDichVu,SoLuong,dv.Gia,ThanhTienDichVu from HoaDonDichVuPhong dvp join DichVu dv on dvp.MaDichVu = dv.MaDichVu
--select pdp.MaDatPhong, p.MaPhong, ThanhTienPhong, TongThanhToan
--from PhieuDatPhong pdp full join Phong p on pdp.MaPhong = p.MaPhong join HoaDonThanhToan hd on pdp.MaDatPhong = hd.MaDatPhong
--where p.MaPhong = 'P005'
--select p.MaPhong,TienDV = SUM(ThanhTienDichVu) ,TienPhong = Sum(ThanhTienPhong), Tong = SUM(TongThanhToan)
--from PhieuDatPhong pdp full join Phong p on pdp.MaPhong = p.MaPhong join HoaDonThanhToan hd on pdp.MaDatPhong = hd.MaDatPhong join HoaDonDichVuPhong dvp on pdp.MaDatPhong=dvp.MaDatPhong
--where NgayThanhToan BETWEEN CAST('2022-05-28' AS DATE) AND CAST('2023-07-10' AS DATE)
--group by p.MaPhong

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
	  -----TEST
--INSERT INTO HoaDonThanhToan (MaHoaDon, MaDatPhong, NgayThanhToan, HinhThucThanhToan, ThanhTienPhong, TongThanhToan, GhiChu) 
--values(N'HD016',N'PDP016','2023/5/2',1, 2000000, 2030000, ''),
--	(N'HD017',N'PDP017','2023/5/2',1, 100000, 5000000, ''),
--	(N'HD018',N'PDP018','2023/5/2',1, 20000, 600000, '')
		--Xem toan bo HoaDonThanhToan
select * from HoaDonThanhToan
--select * from HoaDonThanhToan
--where NgayThanhToan BETWEEN CAST('2023-05-28' AS DATE) AND CAST('2023-07-10' AS DATE)
--Xoa HoaDonThanhToan khi biet MaHoaDon
--delete from HoaDonThanhToan where MaHoaDon = 'HD001'
