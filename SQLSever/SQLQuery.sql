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
	CMT nvarchar(50) not null,
	SDT nvarchar(10) not null,
	Gmail nvarchar(50),
	DiaChi nvarchar(200),
	GioiTinh char(3),
	--Chức vụ
	--0: nhân viên bình thường
	--1: quản lý
	ChucVu bit not null,
	--Giới tính
	Pwd nvarchar(50) not null
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


--INSERT DATA
--Khach Hang
select * from KhachHang
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

--Nhan Vien
select * from NhanVien
INSERT INTO NhanVien values(N'NV001', N'Nguyễn Trà My',12345678801 ,'0967344087','tramy1234@gmail.com',
N'Tỉnh Tiền Giang' ,N'Nu', 0,'tramy6665')
INSERT INTO NhanVien values(N'NV002', N'Nguyễn Huỳnh Bảo', 13598800021, '0134556780', 'huynhbao4444@gmail.com',
N'Tỉnh Tây Ninh', N'Nam', 1, 'huybao2345' )
INSERT INTO NhanVien values(N'NV003', N'Lê Trần Thu Hà', 10445689901, '0860012345', 'thuha4666@gmail.com',
N'Tỉnh An Giang', N'Nu', 1, 'thuha5699')
INSERT INTO NhanVien values(N'NV004', N'Phan Đại Nam', 14577770124, '0497601223', 'dainam1111@gmail.com',
N'Tỉnh Cà Mau', N'Nam', 0, 'namphan0123')
INSERT INTO NhanVien values(N'NV005', N'Trần Thanh Thúy', 14378889012, '0403567790', 'thanhthuy5666@gmail.com',
N'Tỉnh Bình Thuận', N'Nu', 0, 'thuytran2222')
INSERT INTO NhanVien values(N'NV006', N'Trần Công Toàn', 17502254891, '0398865341', 'congtoan0000@gmail.com',
N'Thành Phố Hồ Chí Minh', N'Nam', 1, '123456')
INSERT INTO NhanVien values(N'NV007', N'Nguyễn Công Vinh', 14659801870, '0495012397', 'nguyenvinh1234@gmail.com',
N'Tỉnh Bạc Liêu', N'Nam', 1, '123456')
INSERT INTO NhanVien values(N'NV008', N'Phạm Thị Thanh Vy', 12962007961, '0329276801', 'thanhvy6492@gmail.com',
N'Tỉnh Tiền Giang', N'Nu', 0, '123456')
INSERT INTO NhanVien values(N'NV009', N'Ngô Thị Kim Châu', 13456788990, '0296012578', 'kimchau7777@gmail.com',
N'Tỉnh Ninh Thuận', N'Nu', 1, '123456')
INSERT INTO NhanVien values(N'NV010', N'Phạm Tấn Đạt', 13592590001, '0572176912', 'tandat2953@gmail.com',
N'Tỉnh Kiên Giang', N'Nam', 0, '123456')
INSERT INTO NhanVien values(N'NV011', N'Trần Nam Phương', 15925048123, '0149367012', 'namphuong1492@gmail.com',
N'Tỉnh Châu Đốc', N'Nam', 1, '123456')
INSERT INTO NhanVien values(N'NV012', N'Lê Thanh Thủy', 16932058712, '0471486012', 'thanhthuy4295@gmail.com', 
N'Thành Phố Hồ Chí Minh', N'Nu', 1, '123456')
INSERT INTO NhanVien values(N'NV013', N'Trần Lê Đại Trí', 17032012681, '0247198012', 'daitri5397@gmail.com',
N'Tỉnh Yên Bái', N'Nam', 0, '123456')
INSERT INTO NhanVien values(N'NV014', N'Dương Đăng Khoa', 15920459681, '0247175901', 'dangkhoa7741@gmail.com',
N'Tỉnh An Giang', N'Nam', 0, '123456')
INSERT INTO NhanVien values(N'NV015', N'Trần Thị Thảo My', 16920633811, '0471159801', 'thaomy4291@gmail.com',
N'Thành Phố Hà Nội', N'Nu',1, '123456')



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
values(N'PDP001',N'NV001',N'P001', N'KH001', '2023/3/16', '2023/3/18', '2023/3/22',2,''),
(N'PDP003',N'NV002',N'P003', N'KH003','2023/3/29','2023/4/9', '2023/4/12',2,''),
(N'PDP004',N'NV002',N'P004', N'KH004','2023/4/4','2023/4/6', '2023/4/12',2,''),
(N'PDP002',N'NV001',N'P002', N'KH002','2023/3/27','2023/3/28', '2023/4/2',2,''),
(N'PDP005',N'NV003',N'P001', N'KH005','2023/4/25','2023/4/25', '2023/4/28',1,'')
--select * from PhieuDatPhong
------------------------------------
--INSERT INTO Phong (MaPhong) values(N'P001')
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP001',N'DV001',9)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP001',N'DV003',5)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP001',N'DV004',7)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP001',N'DV005',3)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP002',N'DV001',5)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP002',N'DV004',15)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP003',N'DV002',10)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP003',N'DV005',4)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP004',N'DV004',8)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP004',N'DV005',20)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP005',N'DV003',10)
INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) values(N'PDP005',N'DV004',6)
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

--------------------------------------------------------------------------



INSERT INTO HoaDonThanhToan (MaHoaDon, MaDatPhong, NgayThanhToan, HinhThucThanhToan, ThanhTienPhong, TongThanhToan, GhiChu) 
values(N'HD001',N'PDP001','2023/3/22',1, 2000000, 2030000, ''),
(N'HD002',N'PDP002','2023/4/2',0, 3000000, 3050000, ''),
(N'HD003',N'PDP003','2023/4/12',0, 1000000, 1020000, ''),
(N'HD004',N'PDP004','2023/4/12',1, 3000000, 3050000, ''),
(N'HD005',N'PDP005','2023/4/28',1, 1000000, 1060000, '')

--delete from HoaDonThanhToan where MaHoaDon = 'HD001'
--select * from HoaDonThanhToan