USE [master]
GO
/****** Object:  Database [SHOPDATA]    Script Date: 14/12/2022 8:38:28 CH ******/
CREATE DATABASE [SHOPDATA]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SHOPDATA', FILENAME = N'C:\Users\Hi There\OneDrive\Máy tính\Code\Database\SHOPDATA.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SHOPDATA_log', FILENAME = N'C:\Users\Hi There\OneDrive\Máy tính\Code\Database\SHOPDATA_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SHOPDATA] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SHOPDATA].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SHOPDATA] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SHOPDATA] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SHOPDATA] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SHOPDATA] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SHOPDATA] SET ARITHABORT OFF 
GO
ALTER DATABASE [SHOPDATA] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SHOPDATA] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SHOPDATA] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SHOPDATA] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SHOPDATA] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SHOPDATA] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SHOPDATA] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SHOPDATA] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SHOPDATA] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SHOPDATA] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SHOPDATA] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SHOPDATA] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SHOPDATA] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SHOPDATA] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SHOPDATA] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SHOPDATA] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SHOPDATA] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SHOPDATA] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SHOPDATA] SET  MULTI_USER 
GO
ALTER DATABASE [SHOPDATA] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SHOPDATA] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SHOPDATA] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SHOPDATA] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SHOPDATA] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SHOPDATA] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'SHOPDATA', N'ON'
GO
ALTER DATABASE [SHOPDATA] SET QUERY_STORE = OFF
GO
USE [SHOPDATA]
GO
/****** Object:  Table [dbo].[CAPBAC]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CAPBAC](
	[SoCap] [int] NOT NULL,
	[ghiChu] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[SoCap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CHAMCONG]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHAMCONG](
	[MaNv] [nvarchar](5) NULL,
	[SoGioLam] [int] NULL,
	[Luong] [money] NULL,
	[LuongThuong] [money] NULL,
	[LuongPhat] [money] NULL,
	[TongLuong] [money] NULL,
	[thang] [nvarchar](20) NULL,
	[GhiChu] [nvarchar](500) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DONHANG]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DONHANG](
	[MaDonHang] [int] IDENTITY(1,1) NOT NULL,
	[TongTien] [money] NULL,
	[MaNV] [nvarchar](5) NULL,
	[NgayTaoDon] [date] NULL,
	[SoDT] [varchar](10) NULL,
	[GhiChu] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDonHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DONHANGCHITIET]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DONHANGCHITIET](
	[MaDonHang] [int] NOT NULL,
	[MaSP] [varchar](5) NOT NULL,
	[SoLuong] [int] NULL,
	[ThanhTien] [money] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDonHang] ASC,
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHACHHANG](
	[SoDT] [varchar](10) NOT NULL,
	[HoTen] [nvarchar](50) NULL,
	[GioiTinh] [bit] NULL,
	[Email] [char](50) NULL,
	[DiaChi] [nvarchar](500) NULL,
	[SoCap] [int] NULL,
	[GhiChu] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[SoDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LOAISANPHAM]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAISANPHAM](
	[maLoai] [char](1) NOT NULL,
	[tenLoai] [varchar](12) NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LuuThongTin]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LuuThongTin](
	[MaNv] [nvarchar](5) NULL,
	[MatKhau] [varchar](12) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MaNv] [nvarchar](5) NOT NULL,
	[HoTen] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NULL,
	[SoDT] [varchar](10) NULL,
	[MatKhau] [varchar](12) NULL,
	[NgaySinh] [date] NULL,
	[DiaChi] [nvarchar](500) NULL,
	[VaiTro] [bit] NULL,
	[Img] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNv] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SANPHAM]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAM](
	[MaSP] [varchar](5) NOT NULL,
	[TenSP] [nvarchar](50) NULL,
	[GiaBan] [money] NULL,
	[SoLuong] [int] NULL,
	[HangSanXuat] [nvarchar](100) NULL,
	[maLoai] [char](1) NULL,
	[GhiChu] [nvarchar](500) NULL,
	[Img] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CHAMCONG]  WITH CHECK ADD  CONSTRAINT [FK_ChamCong_NhanVien] FOREIGN KEY([MaNv])
REFERENCES [dbo].[NHANVIEN] ([MaNv])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CHAMCONG] CHECK CONSTRAINT [FK_ChamCong_NhanVien]
GO
ALTER TABLE [dbo].[DONHANG]  WITH CHECK ADD  CONSTRAINT [FK_DonHang_KhachHang] FOREIGN KEY([SoDT])
REFERENCES [dbo].[KHACHHANG] ([SoDT])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[DONHANG] CHECK CONSTRAINT [FK_DonHang_KhachHang]
GO
ALTER TABLE [dbo].[DONHANG]  WITH CHECK ADD  CONSTRAINT [FK_DonHang_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNv])
GO
ALTER TABLE [dbo].[DONHANG] CHECK CONSTRAINT [FK_DonHang_NhanVien]
GO
ALTER TABLE [dbo].[DONHANGCHITIET]  WITH CHECK ADD  CONSTRAINT [FK_DonCT_DonHang] FOREIGN KEY([MaDonHang])
REFERENCES [dbo].[DONHANG] ([MaDonHang])
GO
ALTER TABLE [dbo].[DONHANGCHITIET] CHECK CONSTRAINT [FK_DonCT_DonHang]
GO
ALTER TABLE [dbo].[DONHANGCHITIET]  WITH CHECK ADD  CONSTRAINT [FK_DonCT_SanPham] FOREIGN KEY([MaSP])
REFERENCES [dbo].[SANPHAM] ([MaSP])
GO
ALTER TABLE [dbo].[DONHANGCHITIET] CHECK CONSTRAINT [FK_DonCT_SanPham]
GO
ALTER TABLE [dbo].[KHACHHANG]  WITH CHECK ADD  CONSTRAINT [FK_khachHang_capbac] FOREIGN KEY([SoCap])
REFERENCES [dbo].[CAPBAC] ([SoCap])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[KHACHHANG] CHECK CONSTRAINT [FK_khachHang_capbac]
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD  CONSTRAINT [FK_SanPham_LoaiSP] FOREIGN KEY([maLoai])
REFERENCES [dbo].[LOAISANPHAM] ([maLoai])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[SANPHAM] CHECK CONSTRAINT [FK_SanPham_LoaiSP]
GO
ALTER TABLE [dbo].[DONHANGCHITIET]  WITH CHECK ADD CHECK  (([SoLuong]>=(0)))
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD CHECK  (([SoLuong]>=(0) AND [GiaBan]>=(0)))
GO
/****** Object:  StoredProcedure [dbo].[sp_BieuDo]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_BieuDo]
as begin
	select 
		Sum(SoLuong) soLuong,
		ngayTaoDon,		
		Sum(tongTien) tongTien		
	from donHang a
join DonHangCHiTiet b on a.MaDonHang = b.MaDonHang
where Month(GETDATE()) = Month(ngayTaoDon) and year(NgayTaoDon)= year(GETDATE())
group by NgayTaoDon
order by NgayTaoDon desc
end
GO
/****** Object:  StoredProcedure [dbo].[sp_BieuDoThang]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_BieuDoThang]
as begin
	select   MONTH(NgayTaoDon) as thang ,YEAR(NgayTaoDon)as nam, sum(tongTien) as tongTien from donHang a
join DonHangCHiTiet b on a.MaDonHang = b.MaDonHang
group by MONTH(NgayTaoDon) ,YEAR(NgayTaoDon) 
order by YEAR(NgayTaoDon) desc,MONTH(NgayTaoDon) desc 

end
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeTheoNgay]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_ThongKeTheoNgay]
as begin
	select 
		TenSP,
		sum(dhct.SoLuong) SoLuong,
		TongTien,
		CONVERT(varchar, NgayTaoDon, 105) NgayTaoDon
	from SANPHAM	
		inner join DONHANGCHITIET dhct
		on SANPHAM.MaSP = dhct.MaSP
		inner join DONHANG dh
		on dh.MaDonHang = dhct.MaDonHang
	group by TenSP,NgayTaoDon,TongTien
	order by SoLuong desc
end
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeTheoNhanVien]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_ThongKeTheoNhanVien]
as begin
	select 
		HoTen,
		TenSP,
		sum(dhct.SoLuong) SoLuong,
		CONVERT(varchar, NgayTaoDon, 105) NgayTaoDon
	from SANPHAM
		inner join DONHANGCHITIET dhct
		on SANPHAM.MaSP = dhct.MaSP
		inner join DONHANG dh
		on dh.MaDonHang = dhct.MaDonHang
		inner join NHANVIEN nv
		on nv.MaNV = dh.MaNV
	group by HoTen,TenSP,NgayTaoDon
	order by SoLuong desc
end
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeTheoThang]    Script Date: 14/12/2022 8:38:28 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_ThongKeTheoThang](@Thang int)
as begin
	select 
		TenSP,
		sum(dhct.SoLuong) SoLuong,
		TongTien,
		Month(NgayTaoDon) Thang,
		year(NgayTaoDon) Nam
	from SANPHAM	
		inner join DONHANGCHITIET dhct
		on SANPHAM.MaSP = dhct.MaSP
		inner join DONHANG dh
		on dh.MaDonHang = dhct.MaDonHang
	where MONTH(NgayTaoDon) = @Thang
	group by TenSP,NgayTaoDon,TongTien
	order by  MONTH(NgayTaoDon),year(NgayTaoDon),SoLuong desc
end
GO
USE [master]
GO
ALTER DATABASE [SHOPDATA] SET  READ_WRITE 
GO
