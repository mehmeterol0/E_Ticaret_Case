import React from 'react';
import './About.scss'; 

function About() {
  return (
    <div className="about">
      <h2>Hakkımda</h2>
      <div className="about-content">
        <img src="https://media.licdn.com/dms/image/C4D03AQEyarE4ZpB0_A/profile-displayphoto-shrink_400_400/0/1645785483223?e=1717027200&v=beta&t=Gnw_1aBHgr0Yf8pKVgEpfFvUIMkhOhglV-bMlkyVItw" alt="Profil Resmi" className="profile-image" />
        <div className="about-text">
          <p>
            Eylül 1997 doğumlu, teknoloji ve mühendislik tutkunu bir profesyonelim. 2015 yılında, Dr. Nureddin Erk Mesleki ve Teknik Anadolu Lisesi’nin Bilişim Teknolojileri - Veri Tabanı Programcılığı alanından mezun olarak kariyerime sağlam bir temel attım. Ardından, 2017’de Fatih Sultan Mehmet Vakıf Üniversitesi’nde Biyomedikal Mühendisliği eğitimime başladım ve bu alandaki bilgimi derinleştirdim. 2020 yılında ise, bilgisayar mühendisliği yönündeki tutkumu takip ederek aynı üniversitenin Bilgisayar Mühendisliği Bölümü Yandal Programı’na katıldım.
          </p>
          <p>
            Her gün, “Bugün yaptığınız şey, tüm yarınınızı iyileştirip geliştirebilir” ilkesiyle, sürekli kendimi geliştirme ve öğrenme yolculuğundayım. Teknoloji ve mühendislik alanlarında sürekli gelişen dünyamızda, bilgi ve becerilerimi en yeni yeniliklerle güncel tutarak, sektördeki değişimlere ve zorluklara uyum sağlamayı hedefliyorum.
          </p>
        </div>
      </div>
    </div>
  );
}

export default About;