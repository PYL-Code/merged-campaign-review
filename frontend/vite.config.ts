import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  server: {
    proxy: {
      '/api/mypage': {
        target: 'http://localhost:8000',
        changeOrigin: true,
        secure: false,
      },
      '/api/admin': {
        target: 'http://localhost:8000',
        changeOrigin: true,
        secure: false,
      },
      '/api/auth': {
        target: 'http://localhost:8000',
        changeOrigin: true,
        secure: false,
      },
      '/api/sms': {
        target: 'http://localhost:8000',
        changeOrigin: true,
        secure: false,
      },
      '/api/user': {
        target: 'http://localhost:8000',
        changeOrigin: true,
        secure: false,
      },
      '/api/reviews': {
        target: 'http://localhost:8000',
        changeOrigin: true,
        secure: false,
      },
      '/api/application': {
        target: 'http://localhost:9876',
        changeOrigin: true,
        secure: false,
      },
      '/api/campaign': {
        target: 'http://localhost:9876',
        changeOrigin: true,
        secure: false,
      }
    }
  },
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
})
