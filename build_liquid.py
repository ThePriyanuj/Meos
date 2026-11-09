import base64
import shutil
import os

with open('alpine_b64.txt', 'r') as f:
    alpine_b64 = f.read().strip()

with open('pinstripe_b64.txt', 'r') as f:
    pinstripe_b64 = f.read().strip()

with open('wallpaper_b64.txt', 'r') as f:
    neoclassical_b64 = f.read().strip()

html_content = f'''<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Meos - Longitudinal Water Drop Refraction</title>
  <script src="https://www.gstatic.com/antigravity/web/dev/tailwindcss.min.js"></script>
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Antonio:wght@100;200;300;400;500;600&family=Barlow+Condensed:wght@200;300;400;500&family=Plus+Jakarta+Sans:wght@200;300;400;500;600&display=swap');
    
    * {{
      box-sizing: border-box;
      user-select: none;
      -webkit-user-select: none;
      -webkit-tap-highlight-color: transparent;
      margin: 0;
      padding: 0;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-rendering: geometricPrecision;
    }}

    body {{
      background: #06070a;
      color: #fafafa;
      font-family: 'Plus Jakarta Sans', -apple-system, BlinkMacSystemFont, sans-serif;
    }}

    .phone-chassis {{
      box-shadow: 
        0 0 0 1px rgba(255, 255, 255, 0.16),
        0 0 0 10px #101116,
        0 0 0 13px #20222c,
        0 40px 100px -20px rgba(0, 0, 0, 0.98);
    }}

    .hide-scrollbar::-webkit-scrollbar {{
      display: none;
    }}
    .hide-scrollbar {{
      -ms-overflow-style: none;
      scrollbar-width: none;
    }}

    /* ================= WALLPAPERS & DYNAMIC WATER REFRACTION ================= */
    :root {{
      --wp-alpine: url('data:image/jpeg;base64,{alpine_b64}');
      --wp-pinstripe: url('data:image/jpeg;base64,{pinstripe_b64}');
      --wp-neoclassical: url('data:image/jpeg;base64,{neoclassical_b64}');
      --active-wp: var(--wp-alpine);
    }}

    #phoneContainer {{
      --active-wp: var(--wp-alpine);
    }}

    .bg-alpine {{
      background-image: var(--wp-alpine);
      background-size: cover;
      background-position: center top;
    }}
    .bg-pinstripe {{
      background-image: var(--wp-pinstripe);
      background-size: cover;
      background-position: center;
    }}
    .bg-neoclassical {{
      background-image: var(--wp-neoclassical);
      background-size: cover;
      background-position: center bottom;
    }}
    .bg-oled {{
      background: #000000 !important;
      background-image: none !important;
    }}

    /* ================= LONGITUDINALLY LONG WATER DROP REFRACTION (ZERO BOUNDARY) ================= */
    /* 1. Longitudinally elongated along the Y-axis */
    /* 2. Actually transparent: inherits background and magnifies it (112%) to create liquid lens refraction */
    /* 3. STRICTLY NO BOUNDARY / NO STROKE */
    /* ================= LONGITUDINALLY LONG WATER DROP REFRACTION (ZERO BOUNDARY) ================= */
    .clock-container {{
      height: 178px;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-top: 14px;
      margin-bottom: 6px;
    }}

    .water-drop-numeral-long {{
      font-family: 'Antonio', 'Barlow Condensed', sans-serif;
      font-weight: 300;
      font-size: 136px;
      line-height: 0.95;
      letter-spacing: 0em;
      color: transparent;
      
      /* Longitudinal stretching vertically + expanded latitudinal width horizontally (+10%) */
      display: inline-block;
      transform: scaleY(1.36) scaleX(1.214);
      transform-origin: center;
      
      /* Pure Optical Water Refraction with 3D Spherical Meniscus Caustics (Matching the ':' droplets) */
      background-image: 
        linear-gradient(172deg, rgba(255, 255, 255, 0.70) 0%, rgba(255, 255, 255, 0.12) 28%, rgba(0, 12, 28, 0.04) 65%, rgba(0, 12, 28, 0.55) 100%),
        var(--active-wp);
      background-size: 100% 100%, 150% 150%;
      background-position: center;
      background-blend-mode: overlay, normal;
      -webkit-background-clip: text;
      background-clip: text;
      
      /* STRICTLY NO BORDER / ZERO STROKE */
      -webkit-text-stroke: 0px transparent !important;
      border: none !important;
      outline: none !important;
      
      /* Razor-sharp liquid clarity & contrast */
      filter: 
        contrast(175%) 
        brightness(118%) 
        saturate(140%) 
        drop-shadow(0 1.5px 2px rgba(0, 10, 30, 0.45));
        
      /* Razor-sharp liquid meniscus specular caustics - matching the ':' water drop */
      text-shadow: 
        0 -1px 0.5px rgba(255, 255, 255, 0.98),
        0 1px 0.5px rgba(255, 255, 255, 0.75),
        0 2px 2.5px rgba(0, 10, 25, 0.65);
        
      transition: all 0.22s cubic-bezier(0.2, 0.8, 0.2, 1);
      cursor: pointer;
    }}
    .water-drop-numeral-long:hover {{
      filter: 
        contrast(185%) 
        brightness(124%) 
        saturate(150%) 
        drop-shadow(0 2.5px 3.5px rgba(0, 15, 40, 0.55));
      transform: scaleY(1.39) scaleX(1.24);
    }}

    /* Pure Water Droplet Colon (Zero boundary, Sharp Caustics) */
    .water-drop-colon-dot {{
      width: 12px;
      height: 12px;
      border-radius: 50%;
      background-image: 
        linear-gradient(172deg, rgba(255, 255, 255, 0.85) 0%, rgba(255, 255, 255, 0.15) 30%, rgba(0, 12, 28, 0.05) 60%, rgba(0, 12, 28, 0.6) 100%),
        var(--active-wp);
      background-size: 100% 100%, 160% 160%;
      background-position: center;
      border: none !important;
      outline: none !important;
      box-shadow: 
        inset 0 1px 0.8px rgba(255, 255, 255, 0.95),
        inset 0 -1px 0.8px rgba(0, 0, 0, 0.5),
        0 1.5px 2px rgba(0, 15, 35, 0.4);
      filter: contrast(165%) brightness(115%);
    }}

    /* Light Theme Contrast Enhancements (For Pinstripe atmosphere, Knife-Edge Sharp) */
    .theme-light .water-drop-numeral-long {{
      background-image: 
        linear-gradient(172deg, rgba(255, 255, 255, 0.85) 0%, rgba(255, 255, 255, 0.2) 28%, rgba(10, 25, 60, 0.1) 65%, rgba(10, 25, 60, 0.6) 100%),
        var(--active-wp);
      filter: 
        contrast(175%) 
        brightness(95%) 
        drop-shadow(0 1.5px 2px rgba(10, 25, 60, 0.45));
      text-shadow: 
        0 -0.8px 0.4px rgba(255, 255, 255, 0.98),
        0 1.2px 1.5px rgba(10, 25, 60, 0.55);
    }}
    .theme-light .water-drop-app-text {{
      background-image: 
        linear-gradient(172deg, rgba(255, 255, 255, 0.80) 0%, rgba(255, 255, 255, 0.18) 28%, rgba(10, 25, 60, 0.08) 65%, rgba(10, 25, 60, 0.55) 100%),
        var(--active-wp);
      filter: 
        contrast(170%) 
        brightness(92%) 
        drop-shadow(0 1px 1.5px rgba(10, 25, 60, 0.4));
      text-shadow: 
        0 -0.8px 0.4px rgba(255, 255, 255, 0.95),
        0 1px 1px rgba(10, 25, 60, 0.4);
    }}
    .theme-light .water-drop-colon-dot {{
      box-shadow: 
        inset 0 1px 0.8px rgba(255, 255, 255, 0.98),
        inset 0 -1px 0.8px rgba(10, 25, 60, 0.6),
        0 1.5px 2px rgba(10, 25, 60, 0.4);
    }}

    /* Light Theme All Apps Drawer */
    .theme-light #drawerScreen {{
      background: rgba(235, 232, 222, 0.72) !important;
      color: #1a1a1a !important;
    }}
    .theme-light #drawerScreen input {{
      color: #1a1a1a !important;
    }}
    .theme-light #drawerScreen input::placeholder {{
      color: rgba(20, 25, 35, 0.45) !important;
    }}
    .theme-light #scrubber span {{
      color: rgba(20, 25, 35, 0.55);
    }}
    .theme-light #totalAppsCounter {{
      color: rgba(20, 25, 35, 0.5) !important;
    }}

    /* ================= CLEAR GLASS TEXT APPS (MINIMALIST, ZERO CARDS, RAZOR-SHARP) ================= */
    .water-drop-app-text {{
      position: relative;
      font-family: 'Plus Jakarta Sans', -apple-system, sans-serif;
      font-size: 23px;
      font-weight: 400;
      letter-spacing: 0.19em; /* Expanded latitudinal tracking */
      line-height: 1;
      color: transparent;
      
      /* Pure Optical Water Droplet: Clear see-through letters refracting the wallpaper matching ':' caustics */
      background-image: 
        linear-gradient(172deg, rgba(255, 255, 255, 0.60) 0%, rgba(255, 255, 255, 0.1) 30%, rgba(0, 12, 28, 0.05) 65%, rgba(0, 12, 28, 0.45) 100%),
        var(--active-wp);
      background-size: 100% 100%, 125% 125%;
      background-position: center;
      background-blend-mode: overlay, normal;
      -webkit-background-clip: text;
      background-clip: text;
      
      /* STRICTLY NO BORDER / ZERO STROKE */
      -webkit-text-stroke: 0px transparent !important;
      border: none !important;
      outline: none !important;
      
      filter: 
        contrast(165%) 
        brightness(115%) 
        saturate(130%) 
        drop-shadow(0 1px 1.5px rgba(0, 10, 30, 0.4));
        
      text-shadow: 
        0 -0.8px 0.3px rgba(255, 255, 255, 0.95),
        0 1px 1.2px rgba(0, 10, 25, 0.5);
        
      transition: all 0.2s cubic-bezier(0.2, 0.8, 0.2, 1);
      cursor: pointer;
      display: inline-block;
      user-select: none;
      padding: 2px 0;
    }}
    .water-drop-app-text:hover {{
      filter: 
        contrast(175%) 
        brightness(122%) 
        saturate(140%) 
        drop-shadow(0 1.5px 2px rgba(0, 15, 40, 0.5));
      transform: translateX(4px);
    }}
      transform: translateX(4px);
    }}
    .water-drop-app-text:active {{
      transform: scale(0.96) translateX(2px);
      opacity: 0.85;
    }}

    /* Subtle Liquid Droplet Pill for Delay Badge */
    .water-chip {{
      background: rgba(255, 255, 255, 0.22);
      backdrop-filter: blur(12px) contrast(120%);
      -webkit-backdrop-filter: blur(12px) contrast(120%);
      border: 1px solid rgba(255, 255, 255, 0.45);
      box-shadow: 
        inset 0 1px 1.5px rgba(255, 255, 255, 0.8),
        0 4px 10px rgba(0, 0, 0, 0.2);
      border-radius: 999px;
    }}

    /* Bottom Frosted Glass Dock */
    .water-dock {{
      background: rgba(255, 255, 255, 0.04);
      backdrop-filter: blur(24px) saturate(190%) contrast(105%);
      -webkit-backdrop-filter: blur(24px) saturate(190%) contrast(105%);
      border: none;
      box-shadow: 
        inset 0 1px 0.5px rgba(255, 255, 255, 0.45),
        inset 0 -1px 0.5px rgba(0, 10, 25, 0.35),
        0 10px 28px -6px rgba(0, 10, 24, 0.4);
      border-radius: 26px;
    }}

    /* Mindful Breathing Ring */
    .breathe-ring {{
      animation: pulseBreathe 4s ease-in-out infinite;
    }}
    @keyframes pulseBreathe {{
      0%, 100% {{ transform: scale(1); opacity: 0.88; }}
      50% {{ transform: scale(1.06); opacity: 1; }}
    }}
    .circle-timer {{
      transition: stroke-dashoffset 1s linear;
    }}
  </style>
</head>
<body class="min-h-screen flex flex-col items-center justify-center p-3 sm:p-5">

  <!-- Header Banner -->
  <div class="mb-3 text-center">
    <div class="text-xs uppercase tracking-[0.3em] text-cyan-300 font-semibold flex items-center justify-center space-x-2">
      <span>Meos</span>
      <span class="text-neutral-500">•</span>
      <span class="text-white">Longitudinal Water Drop (No Boundary)</span>
    </div>
    <div class="text-[11px] text-neutral-400 mt-0.5">
      Ultra-tall longitudinal numerals • Zero boundary lines • Pure optical liquid lens
    </div>
  </div>

  <!-- Atmosphere Switcher Bar -->
  <div class="flex items-center space-x-2 mb-4 bg-neutral-900/90 border border-neutral-800 p-1.5 rounded-full text-xs">
    <button onclick="setWallpaper('alpine')" id="btnWpAlpine" class="px-3.5 py-1.2 rounded-full bg-cyan-500/20 text-cyan-300 border border-cyan-400/30 font-medium transition-all">
      🏔️ Alpine Sky
    </button>
    <button onclick="setWallpaper('pinstripe')" id="btnWpPinstripe" class="px-3.5 py-1.2 rounded-full text-neutral-400 hover:text-white transition-all">
      👔 Pinstripe (From Photo)
    </button>
    <button onclick="setWallpaper('neoclassical')" id="btnWpNeo" class="px-3.5 py-1.2 rounded-full text-neutral-400 hover:text-white transition-all">
      🏛️ Neoclassical
    </button>
  </div>

  <!-- Phone Chassis Device Frame (365x750) -->
  <div class="relative w-[365px] h-[750px] rounded-[46px] phone-chassis overflow-hidden flex flex-col select-none transition-all duration-300 bg-alpine" id="phoneContainer">

    <!-- ================= HOME SCREEN ================= -->
    <div id="homeScreen" class="absolute inset-x-0 top-7 bottom-20 px-7 flex flex-col justify-between z-20 transition-all duration-[380ms] ease-[cubic-bezier(0.16,1,0.3,1)]">
      
      <!-- 1. Top Section: Clean Minimalist Date & Longitudinal Water Drop Clock -->
      <div class="pt-2 select-none cursor-pointer flex flex-col items-center" onclick="showToast('Opened Clock & Calendar')">
        <!-- Date Header: Sharp, Crisp Luxury Spacing (No line/status bar above) -->
        <div class="flex items-center justify-center text-[12.5px] font-semibold tracking-[0.28em] uppercase text-white/95 drop-shadow-sm" id="dateHeader">
          <span>Sun, Nov 8</span>
        </div>

        <!-- THE GIANT LONGITUDINAL WATER DROP CLOCK (ZERO BOUNDARY, HARMONIOUS PROPORTIONS) -->
        <div class="clock-container" id="clockWrapper">
          <div class="flex items-center">
            <!-- Hours -->
            <span id="clockHours" class="water-drop-numeral-long">2</span>
            
            <!-- Water Colon Droplets: Harmoniously spaced between Hours and Minutes -->
            <div class="flex flex-col justify-center items-center space-y-10 px-2.5">
              <div class="water-drop-colon-dot"></div>
              <div class="water-drop-colon-dot"></div>
            </div>
            
            <!-- Minutes -->
            <span id="clockMinutes" class="water-drop-numeral-long">40</span>
          </div>
        </div>
      </div>

      <!-- 2. Middle Section: Curated Essentials with Exact, Even Vertical Spacing -->
      <div class="flex-1 flex flex-col justify-center py-2" id="appsSection">
        <div class="flex flex-col space-y-3.5" id="favoriteAppsContainer">
          <!-- Populated by JS with uniform 40px touch rows and identical 14px vertical gaps -->
        </div>
      </div>

    </div>

    <!-- ================= ALL APPS SCREEN ================= -->
    <div id="drawerScreen" class="absolute inset-x-0 top-[96px] bottom-3 px-6 flex flex-col z-20 opacity-0 pointer-events-none translate-y-16 transition-all duration-[380ms] ease-[cubic-bezier(0.16,1,0.3,1)]">
      <!-- Apps List and Alphabet Scrubber -->
      <div class="flex-1 relative flex overflow-hidden">
        <div class="flex-1 overflow-y-auto hide-scrollbar py-2 space-y-2 pr-2" id="allAppsList">
          <!-- Populated by JS -->
        </div>
        <!-- A-Z Alphabet Scrubber -->
        <div class="w-5 flex flex-col items-center justify-between py-1 text-[8px] text-white/50 font-semibold select-none" id="scrubber"></div>
      </div>

      <!-- Drawer Footer (Settings symbol moved here from home screen) -->
      <div class="pt-3 border-t border-white/10 flex justify-between items-center text-xs text-white/70">
        <button onclick="goHome()" class="hover:text-white active:scale-95 transition-all flex items-center space-x-1 py-1 px-2.5 rounded-full hover:bg-white/10">
          <span>‹</span>
          <span>home</span>
        </button>
        <button onclick="openSettings()" class="hover:text-white active:scale-95 transition-all flex items-center space-x-1.5 py-1 px-2.5 rounded-full bg-white/10 hover:bg-white/20" title="Launcher Settings">
          <svg class="w-3.5 h-3.5 fill-current" viewBox="0 0 24 24"><path d="M19.14 12.94c.04-.3.06-.61.06-.94 0-.32-.02-.64-.07-.94l2.03-1.58c.18-.14.23-.41.12-.61l-1.92-3.32c-.12-.22-.37-.29-.59-.22l-2.39.96c-.5-.38-1.03-.7-1.62-.94l-.36-2.54c-.04-.24-.24-.41-.48-.41h-3.84c-.24 0-.43.17-.47.41l-.36 2.54c-.59.24-1.13.57-1.62.94l-2.39-.96c-.22-.08-.47 0-.59.22L2.74 8.87c-.12.21-.08.47.12.61l2.03 1.58c-.05.3-.09.63-.09.94s.02.64.07.94l-2.03 1.58c-.18.14-.23.41-.12.61l1.92 3.32c.12.22.37.29.59.22l2.39-.96c.5.38 1.03.7 1.62.94l.36 2.54c.05.24.24.41.48.41h3.84c.24 0 .44-.17.47-.41l.36-2.54c.59-.24 1.13-.56 1.62-.94l2.39.96c.22.08.47 0 .59-.22l1.92-3.32c.12-.22.07-.47-.12-.61l-2.01-1.58zM12 15.6c-1.98 0-3.6-1.62-3.6-3.6s1.62-3.6 3.6-3.6 3.6 1.62 3.6 3.6-1.62 3.6-3.6 3.6z"/></svg>
          <span class="text-[12px] lowercase tracking-wide font-medium">settings</span>
        </button>
      </div>
    </div>

    <!-- ================= UNIFIED PERSISTENT LIQUID BANNER (TRUE ONE-MOVEMENT GPU MORPH) ================= -->
    <div id="unifiedBanner" class="water-dock absolute left-5 right-5 bottom-4 h-12 z-30 flex items-center justify-between px-3 transition-transform duration-[380ms] ease-[cubic-bezier(0.16,1,0.3,1)]">
      
      <!-- Left Action Slot (Phone on Home <-> Search Glass on Drawer) -->
      <div class="relative w-8 h-8 flex items-center justify-center flex-shrink-0">
        <!-- Home: Minimalist Sharp Phone Action -->
        <button id="bannerPhoneBtn" onclick="onBannerLeftClick(event)" class="absolute inset-0 flex items-center justify-center cursor-pointer p-1.5 rounded-full hover:bg-white/10 active:scale-90 transition-all duration-200 text-white/90" title="Quick Phone">
          <svg class="w-4 h-4 stroke-current" viewBox="0 0 24 24" fill="none" stroke-width="1.6" stroke-linecap="round" stroke-linejoin="round">
            <path d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"/>
          </svg>
        </button>
        <!-- Drawer: Search Glass Icon -->
        <button id="bannerSearchIcon" onclick="onBannerLeftClick(event)" class="absolute inset-0 flex items-center justify-center cursor-pointer p-1.5 rounded-full hover:bg-white/10 active:scale-90 transition-all duration-200 text-white/80 opacity-0 pointer-events-none scale-75" title="Search Apps">
          <svg class="w-4 h-4 fill-current" viewBox="0 0 24 24"><path d="M15.5 14h-.79l-.28-.27A6.471 6.471 0 0 0 16 9.5 6.5 6.5 0 1 0 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/></svg>
        </button>
      </div>

      <!-- Center Slot (Clear Tap Area on Home <-> Real Search Input on Drawer) -->
      <div class="relative flex-1 h-full mx-2 flex items-center overflow-hidden">
        <!-- Home: Subtle Meniscus Glass Tap Area -->
        <div id="bannerHomeTap" onclick="openDrawer()" class="absolute inset-0 flex items-center justify-center cursor-pointer transition-opacity duration-200" title="Slide up for All Apps">
          <div class="w-7 h-0.5 rounded-full bg-white/25 hover:bg-white/45 transition-colors"></div>
        </div>
        <!-- Drawer: Search Input -->
        <div id="bannerSearchBox" class="absolute inset-0 flex items-center opacity-0 pointer-events-none transition-opacity duration-200">
          <input type="text" id="searchInput" placeholder="search apps..." oninput="onSearch(this.value)" 
                 class="bg-transparent border-0 text-white placeholder-white/40 text-[14px] font-normal w-full focus:outline-none tracking-wide">
          <button id="clearSearchBtn" onclick="clearSearch()" class="hidden text-white/60 hover:text-white text-xs px-1">✕</button>
        </div>
      </div>

      <!-- Right Action Slot (UPI Scanner on Home <-> Settings Icon on Drawer) -->
      <div class="relative w-8 h-8 flex items-center justify-center flex-shrink-0">
        <!-- Home: Minimalist Sharp UPI QR Scan Action -->
        <button id="bannerUpiBtn" onclick="onBannerRightClick(event)" class="absolute inset-0 flex items-center justify-center cursor-pointer p-1.5 rounded-full hover:bg-white/10 active:scale-90 transition-all duration-200 text-white/90" title="Scan & Pay (UPI QR)">
          <svg class="w-4 h-4 stroke-current" viewBox="0 0 24 24" fill="none" stroke-width="1.6" stroke-linecap="round" stroke-linejoin="round">
            <path d="M4 8V5a1 1 0 0 1 1-1h3"/>
            <path d="M16 4h3a1 1 0 0 1 1 1v3"/>
            <path d="M20 16v3a1 1 0 0 1-1 1h-3"/>
            <path d="M8 20H5a1 1 0 0 1-1-1v-3"/>
            <rect x="8.5" y="8.5" width="7" height="7" rx="1.2" stroke-width="1.4"/>
            <path d="M12 11v2"/>
          </svg>
        </button>
        <!-- Drawer: Settings Button -->
        <button id="bannerSettingsBtn" onclick="onBannerRightClick(event)" class="absolute inset-0 flex items-center justify-center cursor-pointer p-1.5 rounded-full hover:bg-white/10 active:scale-90 transition-all duration-200 text-white/80 opacity-0 pointer-events-none scale-75" title="Settings">
          <svg class="w-3.5 h-3.5 fill-current" viewBox="0 0 24 24"><path d="M19.14 12.94c.04-.3.06-.61.06-.94 0-.32-.02-.64-.07-.94l2.03-1.58c.18-.14.23-.41.12-.61l-1.92-3.32c-.12-.22-.37-.29-.59-.22l-2.39.96c-.5-.38-1.03-.7-1.62-.94l-.36-2.54c-.04-.24-.24-.41-.48-.41h-3.84c-.24 0-.43.17-.47.41l-.36 2.54c-.59.24-1.13.57-1.62.94l-2.39-.96c-.22-.08-.47 0-.59.22L2.74 8.87c-.12.21-.08.47.12.61l2.03 1.58c-.05.3-.09.63-.09.94s.02.64.07.94l-2.03 1.58c-.18.14-.23.41-.12.61l1.92 3.32c.12.22.37.29.59.22l2.39-.96c.5.38 1.03.7 1.62.94l.36 2.54c.05.24.24.41.48.41h3.84c.24 0 .44-.17.47-.41l.36-2.54c.59-.24 1.13-.56 1.62-.94l2.39.96c.22.08.47 0 .59-.22l1.92-3.32c.12-.22.07-.47-.12-.61l-2.01-1.58zM12 15.6c-1.98 0-3.6-1.62-3.6-3.6s1.62-3.6 3.6-3.6 3.6 1.62 3.6 3.6-1.62 3.6-3.6 3.6z"/></svg>
        </button>
      </div>

    </div>

    <!-- ================= SETTINGS SCREEN ================= -->
    <div id="settingsScreen" class="hidden absolute inset-0 z-40 flex flex-col pt-8 pb-5 px-5 bg-black/85 backdrop-blur-3xl text-white">
      <div class="flex items-center justify-between pb-3 border-b border-white/15">
        <div class="text-[15px] font-medium tracking-wide lowercase">launcher settings</div>
        <button onclick="goHome()" class="text-xs text-white/70 hover:text-white font-medium lowercase">done</button>
      </div>

      <div class="flex-1 overflow-y-auto hide-scrollbar mt-4 space-y-6 text-xs text-white/80">
        <!-- Visual Atmosphere Selection -->
        <div>
          <div class="text-[11px] font-medium tracking-wider text-white/40 lowercase mb-2.5">atmosphere</div>
          <div class="space-y-2">
            <div onclick="setWallpaper('alpine')" class="cursor-pointer water-dock p-3 flex items-center justify-between text-white">
              <div>
                <div class="font-medium lowercase text-[13px]">alpine sky</div>
                <div class="text-[10px] text-white/50 lowercase">radiant azure mountain sky with liquid refraction</div>
              </div>
              <span id="checkAlpine" class="text-white font-medium text-sm">✓</span>
            </div>

            <div onclick="setWallpaper('pinstripe')" class="cursor-pointer water-dock p-3 flex items-center justify-between text-white">
              <div>
                <div class="font-medium lowercase text-[13px]">pinstripe</div>
                <div class="text-[10px] text-white/50 lowercase">vertical navy stripes refracted through liquid numerals</div>
              </div>
              <span id="checkPinstripe" class="text-white font-medium text-sm hidden">✓</span>
            </div>

            <div onclick="setWallpaper('neoclassical')" class="cursor-pointer water-dock p-3 flex items-center justify-between text-white">
              <div>
                <div class="font-medium lowercase text-[13px]">neoclassical</div>
                <div class="text-[10px] text-white/50 lowercase">architectural stone refracted through clear water drops</div>
              </div>
              <span id="checkNeo" class="text-white font-medium text-sm hidden">✓</span>
            </div>
          </div>
        </div>

        <!-- Quick Pay Scanner App Selection -->
        <div>
          <div class="text-[11px] font-medium tracking-wider text-white/40 lowercase mb-2.5">payment scanner</div>
          <div class="space-y-2">
            <div onclick="setPaymentApp('gpay', 'google pay')" class="cursor-pointer water-dock p-3 flex items-center justify-between text-white">
              <div>
                <div class="font-medium lowercase text-[13px]">google pay</div>
                <div class="text-[10px] text-white/50 lowercase">banner scan icon opens google pay directly</div>
              </div>
              <span id="checkPayGpay" class="text-white font-medium text-sm">✓</span>
            </div>

            <div onclick="setPaymentApp('phonepe', 'phonepe')" class="cursor-pointer water-dock p-3 flex items-center justify-between text-white">
              <div>
                <div class="font-medium lowercase text-[13px]">phonepe</div>
                <div class="text-[10px] text-white/50 lowercase">banner scan icon opens phonepe scanner directly</div>
              </div>
              <span id="checkPayPhonepe" class="text-white font-medium text-sm hidden">✓</span>
            </div>

            <div onclick="setPaymentApp('paytm', 'paytm')" class="cursor-pointer water-dock p-3 flex items-center justify-between text-white">
              <div>
                <div class="font-medium lowercase text-[13px]">paytm</div>
                <div class="text-[10px] text-white/50 lowercase">banner scan icon opens paytm scanner directly</div>
              </div>
              <span id="checkPayPaytm" class="text-white font-medium text-sm hidden">✓</span>
            </div>

            <div onclick="setPaymentApp('bhim', 'bhim upi')" class="cursor-pointer water-dock p-3 flex items-center justify-between text-white">
              <div>
                <div class="font-medium lowercase text-[13px]">bhim upi</div>
                <div class="text-[10px] text-white/50 lowercase">banner scan icon opens bhim scanner directly</div>
              </div>
              <span id="checkPayBhim" class="text-white font-medium text-sm hidden">✓</span>
            </div>

            <div onclick="setPaymentApp('system', 'system upi')" class="cursor-pointer water-dock p-3 flex items-center justify-between text-white">
              <div>
                <div class="font-medium lowercase text-[13px]">system upi (auto)</div>
                <div class="text-[10px] text-white/50 lowercase">launches system default payment scanner intent</div>
              </div>
              <span id="checkPaySystem" class="text-white font-medium text-sm hidden">✓</span>
            </div>
          </div>
        </div>

        <!-- About Meos Principles -->
        <div>
          <div class="text-[11px] font-medium tracking-wider text-white/40 lowercase mb-2.5">about meos</div>
          <div class="space-y-2.5 text-[11px] text-white/70 leading-relaxed bg-white/5 p-3.5 rounded-2xl border border-white/10 backdrop-blur-md">
            <div><span class="text-white font-medium lowercase">liquid optics</span> — transparent numerals magnify and refract background</div>
            <div><span class="text-white font-medium lowercase">zero boundary</span> — no cards or outlines cluttering cognitive focus</div>
            <div><span class="text-white font-medium lowercase">mindful pause</span> — breathing delay before opening distracting apps</div>
            <div><span class="text-white font-medium lowercase">private & offline</span> — zero tracking, zero ads, completely local</div>
          </div>
        </div>
      </div>
    </div>

    <!-- ================= MINDFUL DELAY INTERCEPTOR (BREATHING CIRCLE) ================= -->
    <div id="mindfulModal" class="hidden absolute inset-0 z-50 flex flex-col items-center justify-center p-6 text-center bg-black/85 backdrop-blur-3xl">
      <div class="water-dock p-7 w-full max-w-[310px] flex flex-col items-center border-white/40 shadow-2xl text-white">
        <div class="text-[10px] uppercase tracking-[0.2em] text-cyan-400 font-semibold mb-1">Intentional Pause</div>
        <div class="text-xl font-light text-white mb-1">take a breath</div>
        <div class="text-xs text-white/70 mb-6" id="mindfulTargetName">opening YouTube...</div>

        <!-- Breathing Pulsating Circle with Liquid Glow -->
        <div class="relative w-32 h-32 flex items-center justify-center mb-6 breathe-ring">
          <svg class="w-full h-full -rotate-90" viewBox="0 0 100 100">
            <circle cx="50" cy="50" r="44" stroke="rgba(255,255,255,0.12)" stroke-width="3" fill="transparent" />
            <circle id="mindfulCircle" cx="50" cy="50" r="44" stroke="#38bdf8" stroke-width="3.5" fill="transparent" stroke-dasharray="276" stroke-dashoffset="0" stroke-linecap="round" class="circle-timer" />
          </svg>
          <div class="absolute text-3xl font-extralight tracking-tight text-white" id="mindfulSeconds">5</div>
        </div>

        <button id="mindfulProceedBtn" disabled onclick="finishMindfulLaunch()" 
                class="w-full py-2.5 rounded-full border border-white/20 text-white/40 text-xs tracking-wider uppercase font-medium cursor-not-allowed transition-all">
          open anyway
        </button>
        <button onclick="dismissMindful()" class="mt-3 text-xs text-cyan-300 hover:text-white tracking-wider uppercase py-1.5 font-medium">
          stay focused
        </button>
      </div>
    </div>

    <!-- ================= APP CONTEXT MENU ================= -->
    <div id="contextMenu" class="hidden absolute inset-0 z-50 flex flex-col justify-end bg-black/60 backdrop-blur-md" onclick="closeContextMenu()">
      <div class="water-dock rounded-b-none px-6 py-5 space-y-4 text-white" onclick="event.stopPropagation()">
        <div class="flex items-center justify-between pb-2 border-b border-white/15">
          <div class="text-[15px] font-medium lowercase" id="menuAppName">app name</div>
          <button onclick="closeContextMenu()" class="text-xs text-white/60 hover:text-white">✕</button>
        </div>

        <div class="space-y-3 text-xs text-white/85">
          <div onclick="toggleFavorite()" class="cursor-pointer hover:text-cyan-300 flex items-center justify-between py-1">
            <span id="favMenuLabel">add to favorites</span>
            <span>★</span>
          </div>
          <div onclick="toggleMindfulDelay()" class="cursor-pointer hover:text-cyan-300 flex items-center justify-between py-1">
            <span id="delayMenuLabel">set mindful delay (5s)</span>
            <span>⏱️</span>
          </div>
          <div onclick="hideApp()" class="cursor-pointer hover:text-cyan-300 flex items-center justify-between py-1">
            <span>hide app</span>
            <span>👁️‍🗨️</span>
          </div>
          <div onclick="renameApp()" class="cursor-pointer hover:text-cyan-300 flex items-center justify-between py-1">
            <span>rename</span>
            <span>✏️</span>
          </div>
        </div>
      </div>
    </div>

    <!-- Android Home Navigation Bar -->
    <div class="h-4 flex items-center justify-center cursor-pointer z-40 pb-1" onclick="goHome()" title="Tap to return Home">
      <div class="w-24 h-1 bg-white/40 rounded-full hover:bg-white transition-colors"></div>
    </div>

    <!-- Toast Notification -->
    <div id="toast" class="absolute bottom-9 left-7 right-7 water-chip text-white text-xs py-2 px-3 text-center opacity-0 pointer-events-none transition-opacity duration-200 z-50">
      Toast
    </div>

  </div>

  <script>
    // State
    const apps = [
      {{ id: 'phone', name: 'phone', fav: true, hidden: false, delay: 0 }},
      {{ id: 'messages', name: 'messages', fav: true, hidden: false, delay: 0 }},
      {{ id: 'whatsapp', name: 'whatsapp', fav: true, hidden: false, delay: 0 }},
      {{ id: 'camera', name: 'camera', fav: true, hidden: false, delay: 0 }},
      {{ id: 'notes', name: 'notes', fav: false, hidden: false, delay: 0 }},
      {{ id: 'spotify', name: 'spotify', fav: true, hidden: false, delay: 0 }},
      
      {{ id: 'amazon', name: 'amazon', fav: false, hidden: false, delay: 0 }},
      {{ id: 'calculator', name: 'calculator', fav: false, hidden: false, delay: 0 }},
      {{ id: 'calendar', name: 'calendar', fav: false, hidden: false, delay: 0 }},
      {{ id: 'chrome', name: 'chrome', fav: false, hidden: false, delay: 0 }},
      {{ id: 'clock', name: 'clock', fav: false, hidden: false, delay: 0 }},
      {{ id: 'instagram', name: 'instagram', fav: false, hidden: false, delay: 0 }},
      {{ id: 'maps', name: 'maps', fav: false, hidden: false, delay: 0 }},
      {{ id: 'netflix', name: 'netflix', fav: false, hidden: false, delay: 0 }},
      {{ id: 'photos', name: 'photos', fav: false, hidden: false, delay: 0 }},
      {{ id: 'settings', name: 'settings', fav: false, hidden: false, delay: 0 }},
      {{ id: 'twitter', name: 'x (twitter)', fav: false, hidden: false, delay: 0 }},
      {{ id: 'youtube', name: 'youtube', fav: false, hidden: false, delay: 0 }}
    ];

    let currentWallpaper = 'alpine';
    let selectedApp = null;
    let mindfulTarget = null;
    let timerInterval = null;

    // Clock
    function updateClock() {{
      const now = new Date();
      const h = String(now.getHours()).padStart(2, '0');
      const m = String(now.getMinutes()).padStart(2, '0');
      
      const statusClock = document.getElementById('statusClock');
      if (statusClock) statusClock.textContent = `${{h}}:${{m}}`;
      document.getElementById('clockHours').textContent = h;
      document.getElementById('clockMinutes').textContent = m;

      const options = {{ weekday: 'short', month: 'short', day: 'numeric' }};
      document.getElementById('dateHeader').querySelector('span:first-child').textContent = `${{now.toLocaleDateString('en-US', options)}}`;
    }}
    setInterval(updateClock, 1000);
    updateClock();

    // Wallpaper Switcher
    function setWallpaper(wp) {{
      currentWallpaper = wp;
      const phone = document.getElementById('phoneContainer');
      const btnAlpine = document.getElementById('btnWpAlpine');
      const btnPinstripe = document.getElementById('btnWpPinstripe');
      const btnNeo = document.getElementById('btnWpNeo');

      phone.classList.remove('bg-alpine', 'bg-pinstripe', 'bg-neoclassical');
      phone.classList.add(`bg-${{wp}}`);
      phone.style.setProperty('--active-wp', `var(--wp-${{wp}})`);

      btnAlpine.className = wp === 'alpine' 
        ? 'px-3.5 py-1.2 rounded-full bg-cyan-500/20 text-cyan-300 border border-cyan-400/30 font-medium transition-all' 
        : 'px-3.5 py-1.2 rounded-full text-neutral-400 hover:text-white transition-all';
      btnPinstripe.className = wp === 'pinstripe' 
        ? 'px-3.5 py-1.2 rounded-full bg-cyan-500/20 text-cyan-300 border border-cyan-400/30 font-medium transition-all' 
        : 'px-3.5 py-1.2 rounded-full text-neutral-400 hover:text-white transition-all';
      btnNeo.className = wp === 'neoclassical' 
        ? 'px-3.5 py-1.2 rounded-full bg-cyan-500/20 text-cyan-300 border border-cyan-400/30 font-medium transition-all' 
        : 'px-3.5 py-1.2 rounded-full text-neutral-400 hover:text-white transition-all';

      document.getElementById('checkAlpine').classList.toggle('hidden', wp !== 'alpine');
      document.getElementById('checkPinstripe').classList.toggle('hidden', wp !== 'pinstripe');
      document.getElementById('checkNeo').classList.toggle('hidden', wp !== 'neoclassical');

      const isLight = (wp === 'pinstripe');
      const statusBar = document.getElementById('statusBar');
      const dateHeader = document.getElementById('dateHeader');

      if (isLight) {{
        phone.classList.add('theme-light');
        if (statusBar) statusBar.className = 'h-8 px-7 flex items-center justify-between text-[11px] text-neutral-900 font-bold z-30 pointer-events-none pt-1 drop-shadow-sm';
        dateHeader.className = 'flex items-center justify-center text-[12.5px] font-bold text-neutral-900 tracking-[0.28em] uppercase drop-shadow-sm';
      }} else {{
        phone.classList.remove('theme-light');
        if (statusBar) statusBar.className = 'h-8 px-7 flex items-center justify-between text-[11px] text-white/95 font-semibold z-30 pointer-events-none pt-1 drop-shadow-sm';
        dateHeader.className = 'flex items-center justify-center text-[12.5px] font-semibold text-white/95 tracking-[0.28em] uppercase drop-shadow-sm';
      }}

      renderFavorites();
      renderAllApps();
      showToast(`atmosphere: ${{wp}}`);
    }}

    // Render Favorites in PURE WATER DROPLET REFRACTION (Zero boundary!)
    function renderFavorites() {{
      const container = document.getElementById('favoriteAppsContainer');
      const favs = apps.filter(a => a.fav && !a.hidden);
      
      if (favs.length === 0) {{
        container.innerHTML = `<div class="text-xs text-white/50 lowercase text-center py-4">no favorites.<br>open all apps and hold to pin.</div>`;
        return;
      }}

      container.innerHTML = favs.map(app => `
        <div class="flex items-center justify-between group h-10 px-1 cursor-pointer select-none"
             onclick="launchApp('${{app.id}}')"
             oncontextmenu="event.preventDefault(); openContextMenu('${{app.id}}')">
          <!-- Pure Water Droplet Text with ZERO boundary & Uniform Row Spacing -->
          <span class="water-drop-app-text lowercase">${{app.name}}</span>
        </div>
      `).join('');
    }}

    // Render All Apps in Drawer
    function renderAllApps(filter = '') {{
      const container = document.getElementById('allAppsList');
      const filtered = apps
        .filter(a => !a.hidden && a.name.toLowerCase().includes(filter.toLowerCase()))
        .sort((a, b) => a.name.localeCompare(b.name));

      const counter = document.getElementById('totalAppsCounter'); if (counter) counter.textContent = `${{filtered.length}} apps`;

      if (filtered.length === 0) {{
        container.innerHTML = `<div class="text-xs text-white/50 py-10 text-center">no matching apps</div>`;
        return;
      }}

      container.innerHTML = filtered.map(app => `
        <div class="flex items-center justify-between group h-10 px-2 cursor-pointer select-none hover:bg-white/5 transition-colors"
             onclick="launchApp('${{app.id}}')"
             oncontextmenu="event.preventDefault(); openContextMenu('${{app.id}}')">
          <!-- Matching Home Page Clear Water Drop Text with ZERO card/border -->
          <span class="water-drop-app-text lowercase text-[20px]">${{app.name}}</span>
        </div>
      `).join('');
    }}

    // Setup A-Z Scrubber
    function setupScrubber() {{
      const letters = 'abcdefghijklmnopqrstuvwxyz'.split('');
      const scrubber = document.getElementById('scrubber');
      scrubber.innerHTML = letters.map(l => `
        <span onclick="scrollToLetter('${{l}}')" class="cursor-pointer hover:text-cyan-300">${{l}}</span>
      `).join('');
    }}

    function scrollToLetter(letter) {{
      const match = apps.find(a => !a.hidden && a.name.toLowerCase().startsWith(letter));
      if (match) {{
        showToast(`Jump to '${{letter}}'`);
      }}
    }}

    // App Launch & Mindful Interception
    function launchApp(id) {{
      const app = apps.find(a => a.id === id);
      if (!app) return;

      if (app.delay > 0) {{
        startMindfulDelay(app);
      }} else {{
        showToast(`Launched ${{app.name}}`);
        goHome();
      }}
    }}

    function startMindfulDelay(app) {{
      mindfulTarget = app;
      const modal = document.getElementById('mindfulModal');
      const targetName = document.getElementById('mindfulTargetName');
      const secondsLabel = document.getElementById('mindfulSeconds');
      const proceedBtn = document.getElementById('mindfulProceedBtn');
      const circle = document.getElementById('mindfulCircle');

      targetName.textContent = `opening ${{app.name}}...`;
      proceedBtn.disabled = true;
      proceedBtn.className = 'w-full py-2.5 rounded-full border border-white/20 text-white/40 text-xs tracking-wider uppercase font-medium cursor-not-allowed transition-all';

      let remaining = app.delay;
      secondsLabel.textContent = remaining;
      modal.classList.remove('hidden');

      const totalDash = 276;
      circle.style.strokeDashoffset = '0';

      clearInterval(timerInterval);
      timerInterval = setInterval(() => {{
        remaining--;
        secondsLabel.textContent = remaining;
        const progress = (app.delay - remaining) / app.delay;
        circle.style.strokeDashoffset = String(progress * totalDash);

        if (remaining <= 0) {{
          clearInterval(timerInterval);
          secondsLabel.textContent = '✓';
          proceedBtn.disabled = false;
          proceedBtn.className = 'w-full py-2.5 rounded-full bg-cyan-400 text-black text-xs tracking-wider uppercase font-semibold transition-all shadow-lg shadow-cyan-500/30';
        }}
      }}, 1000);
    }}

    function finishMindfulLaunch() {{
      if (!mindfulTarget) return;
      dismissMindful();
      showToast(`Opened ${{mindfulTarget.name}} intentionally`);
      goHome();
    }}

    function dismissMindful() {{
      clearInterval(timerInterval);
      document.getElementById('mindfulModal').classList.add('hidden');
      mindfulTarget = null;
    }}

    // Navigation (Unified Persistent Liquid Banner)
    let isDrawerOpen = false;

    function onBannerLeftClick(e) {{
      if (e) e.stopPropagation();
      if (isDrawerOpen) {{
        const input = document.getElementById('searchInput');
        if (input) input.focus();
      }} else {{
        quickDial();
      }}
    }}

    function onBannerRightClick(e) {{
      if (e) e.stopPropagation();
      if (isDrawerOpen) {{
        openSettings();
      }} else {{
        openUpiScanner();
      }}
    }}

    function openDrawer() {{
      if (isDrawerOpen) return;
      isDrawerOpen = true;

      const banner = document.getElementById('unifiedBanner');
      const homeScreen = document.getElementById('homeScreen');
      const drawerScreen = document.getElementById('drawerScreen');

      // Exact pixel distance from bottom resting position to top search bar position
      const targetTop = 38;
      const currentTop = banner.offsetTop;
      const travelDistance = currentTop - targetTop;

      // 1. Unified Banner ascends in one continuous physics movement
      banner.style.transform = `translate3d(0, -${{travelDistance}}px, 0)`;

      // 2. Interior Icon Morph (Phone -> Search, Glass -> Input, UPI -> Settings)
      document.getElementById('bannerPhoneBtn').classList.add('opacity-0', 'pointer-events-none', 'scale-75');
      document.getElementById('bannerSearchIcon').classList.remove('opacity-0', 'pointer-events-none', 'scale-75');
      document.getElementById('bannerHomeTap').classList.add('opacity-0', 'pointer-events-none');
      document.getElementById('bannerSearchBox').classList.remove('opacity-0', 'pointer-events-none');
      document.getElementById('bannerUpiBtn').classList.add('opacity-0', 'pointer-events-none', 'scale-75');
      document.getElementById('bannerSettingsBtn').classList.remove('opacity-0', 'pointer-events-none', 'scale-75');

      // 3. Home Screen Slides Out Upward in Exact Sync
      homeScreen.style.transform = 'translate3d(0, -64px, 0)';
      homeScreen.style.opacity = '0';
      homeScreen.style.pointerEvents = 'none';

      // 4. Drawer Screen Slides In Upward in Exact Sync
      drawerScreen.style.transform = 'translate3d(0, 0, 0)';
      drawerScreen.style.opacity = '1';
      drawerScreen.style.pointerEvents = 'auto';

      renderAllApps();
      setTimeout(() => {{
        const input = document.getElementById('searchInput');
        if (input && isDrawerOpen) input.focus();
      }}, 350);
    }}

    function goHome() {{
      if (!document.getElementById('settingsScreen').classList.contains('hidden')) {{
        document.getElementById('settingsScreen').classList.add('hidden');
        return;
      }}
      if (!isDrawerOpen) return;
      isDrawerOpen = false;

      const banner = document.getElementById('unifiedBanner');
      const homeScreen = document.getElementById('homeScreen');
      const drawerScreen = document.getElementById('drawerScreen');

      // 1. Unified Banner descends back down in one continuous physics movement
      banner.style.transform = 'translate3d(0, 0, 0)';

      // 2. Interior Icon Morph Reversal (Search -> Phone, Input -> Glass, Settings -> UPI)
      document.getElementById('bannerPhoneBtn').classList.remove('opacity-0', 'pointer-events-none', 'scale-75');
      document.getElementById('bannerSearchIcon').classList.add('opacity-0', 'pointer-events-none', 'scale-75');
      document.getElementById('bannerHomeTap').classList.remove('opacity-0', 'pointer-events-none');
      document.getElementById('bannerSearchBox').classList.add('opacity-0', 'pointer-events-none');
      document.getElementById('bannerUpiBtn').classList.remove('opacity-0', 'pointer-events-none', 'scale-75');
      document.getElementById('bannerSettingsBtn').classList.add('opacity-0', 'pointer-events-none', 'scale-75');

      // 3. Drawer Screen Slides Out Downward in Exact Sync
      drawerScreen.style.transform = 'translate3d(0, 64px, 0)';
      drawerScreen.style.opacity = '0';
      drawerScreen.style.pointerEvents = 'none';

      // 4. Home Screen Slides In Downward in Exact Sync
      homeScreen.style.transform = 'translate3d(0, 0, 0)';
      homeScreen.style.opacity = '1';
      homeScreen.style.pointerEvents = 'auto';

      // Reset Search
      const input = document.getElementById('searchInput');
      if (input) {{
        input.value = '';
        input.blur();
      }}
      document.getElementById('clearSearchBtn').classList.add('hidden');
    }}

    let selectedPaymentApp = 'gpay';
    let selectedPaymentAppName = 'google pay';

    function setPaymentApp(key, name) {{
      selectedPaymentApp = key;
      selectedPaymentAppName = name;

      document.getElementById('checkPayGpay').classList.toggle('hidden', key !== 'gpay');
      document.getElementById('checkPayPhonepe').classList.toggle('hidden', key !== 'phonepe');
      document.getElementById('checkPayPaytm').classList.toggle('hidden', key !== 'paytm');
      document.getElementById('checkPayBhim').classList.toggle('hidden', key !== 'bhim');
      document.getElementById('checkPaySystem').classList.toggle('hidden', key !== 'system');

      showToast(`scanner: ${{name}}`);
    }}

    function openUpiScanner() {{
      // Directly opens the scanner of the chosen online payment app
      showToast(`opening ${{selectedPaymentAppName}} scanner...`);
    }}

    function closeUpiScanner() {{}}

    function openSettings() {{
      document.getElementById('settingsScreen').classList.remove('hidden');
    }}

    function quickDial() {{
      showToast('Opened Phone Dialer');
    }}

    // Search
    function onSearch(query) {{
      const clearBtn = document.getElementById('clearSearchBtn');
      clearBtn.classList.toggle('hidden', query.trim().length === 0);
      renderAllApps(query);
    }}

    function clearSearch() {{
      const input = document.getElementById('searchInput');
      input.value = '';
      input.focus();
      onSearch('');
    }}

    // Context Menu
    function openContextMenu(id) {{
      selectedApp = apps.find(a => a.id === id);
      if (!selectedApp) return;

      document.getElementById('menuAppName').textContent = selectedApp.name;
      document.getElementById('favMenuLabel').textContent = selectedApp.fav ? 'remove from favorites' : 'add to favorites';
      document.getElementById('delayMenuLabel').textContent = selectedApp.delay > 0 ? `change delay (now ${{selectedApp.delay}}s)` : 'set mindful delay (5s)';
      document.getElementById('contextMenu').classList.remove('hidden');
    }}

    function closeContextMenu() {{
      document.getElementById('contextMenu').classList.add('hidden');
      selectedApp = null;
    }}

    function toggleFavorite() {{
      if (!selectedApp) return;
      selectedApp.fav = !selectedApp.fav;
      closeContextMenu();
      renderFavorites();
      renderAllApps(document.getElementById('searchInput').value);
      showToast(`${{selectedApp.name}} ${{selectedApp.fav ? 'added to' : 'removed from'}} favorites`);
    }}

    function toggleMindfulDelay() {{
      if (!selectedApp) return;
      if (selectedApp.delay === 0) selectedApp.delay = 5;
      else if (selectedApp.delay === 5) selectedApp.delay = 15;
      else selectedApp.delay = 0;

      closeContextMenu();
      renderFavorites();
      renderAllApps(document.getElementById('searchInput').value);
      showToast(`${{selectedApp.name}} delay set to ${{selectedApp.delay}}s`);
    }}

    function hideApp() {{
      if (!selectedApp) return;
      selectedApp.hidden = true;
      closeContextMenu();
      renderFavorites();
      renderAllApps(document.getElementById('searchInput').value);
      showToast(`Hidden ${{selectedApp.name}}`);
    }}

    function renameApp() {{
      if (!selectedApp) return;
      const newName = prompt(`Rename ${{selectedApp.name}}:`, selectedApp.name);
      if (newName && newName.trim()) {{
        selectedApp.name = newName.trim();
        renderFavorites();
    renderAllApps();
        renderAllApps(document.getElementById('searchInput').value);
      }}
      closeContextMenu();
    }}

    // Toast
    let toastTimer;
    function showToast(msg) {{
      const toast = document.getElementById('toast');
      toast.textContent = msg;
      toast.style.opacity = '1';
      clearTimeout(toastTimer);
      toastTimer = setTimeout(() => {{
        toast.style.opacity = '0';
      }}, 2000);
    }}

    // ================= SLIDE UP / SCROLL UP TO OPEN ALL APPS =================
    let touchStartY = 0;
    let touchStartX = 0;
    let isSwiping = false;

    const phoneContainer = document.getElementById('phoneContainer');

    function isHomeScreenActive() {{
      const settings = document.getElementById('settingsScreen');
      return !isDrawerOpen && (!settings || settings.classList.contains('hidden'));
    }}

    // 1. Mouse Wheel & Trackpad Scroll on Home Screen
    phoneContainer.addEventListener('wheel', (e) => {{
      if (!isHomeScreenActive()) return;
      // Any scroll action on the home screen opens the all apps catalog
      if (Math.abs(e.deltaY) > 6 || Math.abs(e.deltaX) > 6) {{
        openDrawer();
      }}
    }}, {{ passive: true }});

    // 2. Touch Swiping Up (Mobile & Touch Devices)
    phoneContainer.addEventListener('touchstart', (e) => {{
      if (!isHomeScreenActive()) return;
      touchStartY = e.touches[0].clientY;
      touchStartX = e.touches[0].clientX;
      isSwiping = true;
    }}, {{ passive: true }});

    window.addEventListener('touchend', (e) => {{
      if (!isSwiping) return;
      isSwiping = false;
      if (!e.changedTouches || e.changedTouches.length === 0) return;
      const deltaY = touchStartY - e.changedTouches[0].clientY; // Positive when swiping up
      const deltaX = Math.abs(touchStartX - e.changedTouches[0].clientX);

      // Slide up: 20px+ vertical upward drag
      if (deltaY > 20 && deltaY > deltaX * 0.4) {{
        openDrawer();
      }}
    }}, {{ passive: true }});

    // 3. Desktop Mouse / Pointer Dragging Up
    phoneContainer.addEventListener('pointerdown', (e) => {{
      if (!isHomeScreenActive()) return;
      touchStartY = e.clientY;
      touchStartX = e.clientX;
      isSwiping = true;
    }});

    window.addEventListener('pointerup', (e) => {{
      if (!isSwiping) return;
      isSwiping = false;
      const deltaY = touchStartY - e.clientY;
      const deltaX = Math.abs(touchStartX - e.clientX);

      if (deltaY > 20 && deltaY > deltaX * 0.4) {{
        openDrawer();
      }}
    }});

    // Swipe Down / Scroll Down in App Drawer to Return Home
    const drawerScreen = document.getElementById('drawerScreen');
    const allAppsList = document.getElementById('allAppsList');
    let drawerTouchStartY = 0;
    let drawerTouchStartX = 0;
    let isDrawerSwiping = false;

    drawerScreen.addEventListener('touchstart', (e) => {{
      if (!isDrawerOpen) return;
      drawerTouchStartY = e.touches[0].clientY;
      drawerTouchStartX = e.touches[0].clientX;
      isDrawerSwiping = true;
    }}, {{ passive: true }});

    drawerScreen.addEventListener('touchend', (e) => {{
      if (!isDrawerSwiping) return;
      isDrawerSwiping = false;
      const touchEndY = e.changedTouches[0].clientY;
      const touchEndX = e.changedTouches[0].clientX;
      const deltaY = touchEndY - drawerTouchStartY; // positive when dragging DOWN
      const deltaX = Math.abs(drawerTouchStartX - touchEndX);

      // If at or near top of the apps list and swiped down by 35px+
      if (allAppsList.scrollTop <= 15 && deltaY > 35 && deltaY > deltaX) {{
        goHome();
      }}
    }}, {{ passive: true }});

    // Desktop Mouse Drag down in drawer to Return Home
    drawerScreen.addEventListener('mousedown', (e) => {{
      if (!isDrawerOpen) return;
      drawerTouchStartY = e.clientY;
      drawerTouchStartX = e.clientX;
      isDrawerSwiping = true;
    }});

    drawerScreen.addEventListener('mouseup', (e) => {{
      if (!isDrawerSwiping) return;
      isDrawerSwiping = false;
      const deltaY = e.clientY - drawerTouchStartY;
      const deltaX = Math.abs(drawerTouchStartX - e.clientX);

      if (allAppsList.scrollTop <= 15 && deltaY > 35 && deltaY > deltaX) {{
        goHome();
      }}
    }});

    // Init
    setupScrubber();
    renderFavorites();
    renderAllApps();
  </script>
</body>
</html>
'''

with open('simulator.html', 'w', encoding='utf-8') as f:
    f.write(html_content)

print("Success: Generated Meos Longitudinal Water Drop Refraction Launcher (No Boundary)!")
