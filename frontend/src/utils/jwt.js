// src/utils/jwt.js
import { jwtDecode } from 'jwt-decode'

export function getUserFromToken() {
    const token = localStorage.getItem('token')
    if (!token) return null

    try {
        const decoded = jwtDecode(token)
        // console.log('디코딩된 토큰:', decoded)
        return decoded.userNo // 백엔드 JWT에 userNo라는 필드가 있다고 가정
    } catch (e) {
        console.error('JWT 디코딩 실패:', e)
        return null
    }
}

export function getUserNo() {
    const userNo = localStorage.getItem('userNo')
    // console.log('userNo:', userNo)
    return userNo ? Number(userNo) : null
}