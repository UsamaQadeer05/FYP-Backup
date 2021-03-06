USE [master]
GO
/****** Object:  Database [PHP_FYP]    Script Date: 7/9/2021 9:35:36 AM ******/
CREATE DATABASE [PHP_FYP]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PHP_FYP', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\PHP_FYP.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'PHP_FYP_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\PHP_FYP_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [PHP_FYP] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PHP_FYP].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PHP_FYP] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PHP_FYP] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PHP_FYP] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PHP_FYP] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PHP_FYP] SET ARITHABORT OFF 
GO
ALTER DATABASE [PHP_FYP] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PHP_FYP] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [PHP_FYP] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PHP_FYP] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PHP_FYP] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PHP_FYP] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PHP_FYP] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PHP_FYP] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PHP_FYP] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PHP_FYP] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PHP_FYP] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PHP_FYP] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PHP_FYP] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PHP_FYP] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PHP_FYP] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PHP_FYP] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PHP_FYP] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PHP_FYP] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PHP_FYP] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PHP_FYP] SET  MULTI_USER 
GO
ALTER DATABASE [PHP_FYP] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PHP_FYP] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PHP_FYP] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PHP_FYP] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [PHP_FYP]
GO
/****** Object:  Table [dbo].[tb_Allergies]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_Allergies](
	[a_id] [int] IDENTITY(1,1) NOT NULL,
	[a_name] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_tb_Allergies] PRIMARY KEY CLUSTERED 
(
	[a_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_Diseases]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_Diseases](
	[d_id] [int] IDENTITY(1,1) NOT NULL,
	[d_name] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_tb_Diseases] PRIMARY KEY CLUSTERED 
(
	[d_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_Medications]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_Medications](
	[m_id] [int] IDENTITY(1,1) NOT NULL,
	[m_name] [nvarchar](100) NOT NULL,
	[m_company] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_tb_Medications] PRIMARY KEY CLUSTERED 
(
	[m_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_Symptoms]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_Symptoms](
	[s_id] [int] IDENTITY(1,1) NOT NULL,
	[s_name] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_tb_Symptoms] PRIMARY KEY CLUSTERED 
(
	[s_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_User_Allergies]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_User_Allergies](
	[ua_id] [int] IDENTITY(1,1) NOT NULL,
	[u_id] [int] NOT NULL,
	[a_id] [int] NOT NULL,
	[ua_Level] [nvarchar](100) NULL,
	[ua_StartDate] [nvarchar](100) NULL,
	[lastUpdated] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_User_Allergies] PRIMARY KEY CLUSTERED 
(
	[ua_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_User_Diseases]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_User_Diseases](
	[ud_id] [int] IDENTITY(1,1) NOT NULL,
	[u_id] [int] NULL,
	[d_id] [int] NULL,
	[ud_level] [nvarchar](100) NULL,
	[ud_Status] [nvarchar](100) NULL,
	[ud_StartDate] [nvarchar](100) NULL,
	[ud_EndDate] [nvarchar](100) NULL,
	[lastUpdated] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_User_Diseases] PRIMARY KEY CLUSTERED 
(
	[ud_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_User_Medications]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_User_Medications](
	[um_id] [int] IDENTITY(1,1) NOT NULL,
	[u_id] [int] NULL,
	[d_id] [int] NULL,
	[m_id] [int] NULL,
	[um_dosage] [int] NULL,
	[um_StartDate] [nvarchar](100) NULL,
	[um_EndDate] [nvarchar](100) NULL,
	[lastUpdated] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_User_Medications] PRIMARY KEY CLUSTERED 
(
	[um_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_User_Social_History]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_User_Social_History](
	[sh_id] [int] IDENTITY(1,1) NOT NULL,
	[u_id] [int] NULL,
	[d_id] [int] NULL,
	[s_level] [nvarchar](100) NULL,
	[s_relation] [nvarchar](100) NULL,
	[lastUpdated] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_User_Social_History] PRIMARY KEY CLUSTERED 
(
	[sh_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_User_Symptoms]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_User_Symptoms](
	[us_id] [int] IDENTITY(1,1) NOT NULL,
	[u_id] [int] NULL,
	[s_id] [int] NULL,
	[us_StartDate] [nvarchar](100) NULL,
	[us_EndDate] [nvarchar](100) NULL,
	[lastUpdated] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_User_Symptoms] PRIMARY KEY CLUSTERED 
(
	[us_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_User_Vaccinations]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_User_Vaccinations](
	[uv_id] [int] IDENTITY(1,1) NOT NULL,
	[u_id] [int] NULL,
	[v_id] [int] NULL,
	[uv_dosage] [nvarchar](100) NULL,
	[uv_injecteddate] [nvarchar](100) NULL,
	[lastUpdated] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_User_Vaccinations] PRIMARY KEY CLUSTERED 
(
	[uv_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_User_Vitals]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_User_Vitals](
	[uvi_id] [int] IDENTITY(1,1) NOT NULL,
	[u_id] [int] NULL,
	[uvi_temperature] [nvarchar](100) NULL,
	[uvi_pulserate] [nvarchar](100) NULL,
	[uvi_respirationrate] [nvarchar](100) NULL,
	[uvi_bloodpressure] [nvarchar](100) NULL,
	[uvi_weight] [nvarchar](100) NULL,
	[uvi_height] [nvarchar](100) NULL,
	[lastUpdated] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_User_Vitals] PRIMARY KEY CLUSTERED 
(
	[uvi_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_Users]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_Users](
	[u_id] [int] IDENTITY(1,1) NOT NULL,
	[u_name] [nvarchar](max) NULL,
	[u_cnic] [nvarchar](max) NULL,
	[u_gender] [nvarchar](100) NULL,
	[u_dob] [nvarchar](100) NULL,
	[u_country] [nvarchar](100) NULL,
	[u_province] [nvarchar](100) NULL,
	[u_city] [nvarchar](100) NULL,
	[u_password] [nvarchar](max) NULL,
	[u_waterfac] [nvarchar](100) NULL,
	[u_area] [nvarchar](100) NULL,
	[u_home] [nvarchar](100) NULL,
	[u_food] [nvarchar](100) NULL,
	[u_ventilation] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_Users] PRIMARY KEY CLUSTERED 
(
	[u_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tb_Vaccination]    Script Date: 7/9/2021 9:35:36 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_Vaccination](
	[v_id] [int] IDENTITY(1,1) NOT NULL,
	[v_name] [nvarchar](100) NOT NULL,
	[v_company] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_tb_Vaccination] PRIMARY KEY CLUSTERED 
(
	[v_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[tb_User_Allergies]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Allergies_tb_Allergies] FOREIGN KEY([a_id])
REFERENCES [dbo].[tb_Allergies] ([a_id])
GO
ALTER TABLE [dbo].[tb_User_Allergies] CHECK CONSTRAINT [FK_tb_User_Allergies_tb_Allergies]
GO
ALTER TABLE [dbo].[tb_User_Allergies]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Allergies_tb_Users] FOREIGN KEY([u_id])
REFERENCES [dbo].[tb_Users] ([u_id])
GO
ALTER TABLE [dbo].[tb_User_Allergies] CHECK CONSTRAINT [FK_tb_User_Allergies_tb_Users]
GO
ALTER TABLE [dbo].[tb_User_Diseases]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Diseases_tb_Diseases] FOREIGN KEY([d_id])
REFERENCES [dbo].[tb_Diseases] ([d_id])
GO
ALTER TABLE [dbo].[tb_User_Diseases] CHECK CONSTRAINT [FK_tb_User_Diseases_tb_Diseases]
GO
ALTER TABLE [dbo].[tb_User_Diseases]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Diseases_tb_Users] FOREIGN KEY([u_id])
REFERENCES [dbo].[tb_Users] ([u_id])
GO
ALTER TABLE [dbo].[tb_User_Diseases] CHECK CONSTRAINT [FK_tb_User_Diseases_tb_Users]
GO
ALTER TABLE [dbo].[tb_User_Medications]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Medications_tb_Diseases] FOREIGN KEY([d_id])
REFERENCES [dbo].[tb_Diseases] ([d_id])
GO
ALTER TABLE [dbo].[tb_User_Medications] CHECK CONSTRAINT [FK_tb_User_Medications_tb_Diseases]
GO
ALTER TABLE [dbo].[tb_User_Medications]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Medications_tb_Medications] FOREIGN KEY([m_id])
REFERENCES [dbo].[tb_Medications] ([m_id])
GO
ALTER TABLE [dbo].[tb_User_Medications] CHECK CONSTRAINT [FK_tb_User_Medications_tb_Medications]
GO
ALTER TABLE [dbo].[tb_User_Medications]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Medications_tb_Users] FOREIGN KEY([u_id])
REFERENCES [dbo].[tb_Users] ([u_id])
GO
ALTER TABLE [dbo].[tb_User_Medications] CHECK CONSTRAINT [FK_tb_User_Medications_tb_Users]
GO
ALTER TABLE [dbo].[tb_User_Social_History]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Social_History_tb_Diseases] FOREIGN KEY([d_id])
REFERENCES [dbo].[tb_Diseases] ([d_id])
GO
ALTER TABLE [dbo].[tb_User_Social_History] CHECK CONSTRAINT [FK_tb_User_Social_History_tb_Diseases]
GO
ALTER TABLE [dbo].[tb_User_Social_History]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Social_History_tb_Users] FOREIGN KEY([u_id])
REFERENCES [dbo].[tb_Users] ([u_id])
GO
ALTER TABLE [dbo].[tb_User_Social_History] CHECK CONSTRAINT [FK_tb_User_Social_History_tb_Users]
GO
ALTER TABLE [dbo].[tb_User_Symptoms]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Symptoms_tb_Symptoms] FOREIGN KEY([s_id])
REFERENCES [dbo].[tb_Symptoms] ([s_id])
GO
ALTER TABLE [dbo].[tb_User_Symptoms] CHECK CONSTRAINT [FK_tb_User_Symptoms_tb_Symptoms]
GO
ALTER TABLE [dbo].[tb_User_Symptoms]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Symptoms_tb_Users] FOREIGN KEY([u_id])
REFERENCES [dbo].[tb_Users] ([u_id])
GO
ALTER TABLE [dbo].[tb_User_Symptoms] CHECK CONSTRAINT [FK_tb_User_Symptoms_tb_Users]
GO
ALTER TABLE [dbo].[tb_User_Vaccinations]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Vaccinations_tb_Users] FOREIGN KEY([u_id])
REFERENCES [dbo].[tb_Users] ([u_id])
GO
ALTER TABLE [dbo].[tb_User_Vaccinations] CHECK CONSTRAINT [FK_tb_User_Vaccinations_tb_Users]
GO
ALTER TABLE [dbo].[tb_User_Vaccinations]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Vaccinations_tb_Vaccination] FOREIGN KEY([v_id])
REFERENCES [dbo].[tb_Vaccination] ([v_id])
GO
ALTER TABLE [dbo].[tb_User_Vaccinations] CHECK CONSTRAINT [FK_tb_User_Vaccinations_tb_Vaccination]
GO
ALTER TABLE [dbo].[tb_User_Vitals]  WITH CHECK ADD  CONSTRAINT [FK_tb_User_Vitals_tb_Users] FOREIGN KEY([u_id])
REFERENCES [dbo].[tb_Users] ([u_id])
GO
ALTER TABLE [dbo].[tb_User_Vitals] CHECK CONSTRAINT [FK_tb_User_Vitals_tb_Users]
GO
USE [master]
GO
ALTER DATABASE [PHP_FYP] SET  READ_WRITE 
GO
