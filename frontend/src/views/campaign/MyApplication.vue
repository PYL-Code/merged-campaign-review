<template>
  <div class="my-application-wrapper">
    <h2 class="page-title">나의 체험단 신청 목록</h2>

    <!-- 필터 버튼 -->
    <div class="status-filter">
      <button @click="filterApplications('ALL')" :class="{ active: filter === 'ALL' }">전체</button>
      <button @click="filterApplications('PENDING')" :class="{ active: filter === 'PENDING' }">처리 중</button>
      <button @click="filterApplications('APPROVED')" :class="{ active: filter === 'APPROVED' }">선정됨</button>
      <button @click="filterApplications('REJECTED')" :class="{ active: filter === 'REJECTED' }">미선정</button>
    </div>

    <div v-if="applications.length === 0" class="empty-message">
      신청한 캠페인이 없습니다.
    </div>

    <div
        v-for="application in filteredApplications"
        :key="application.applicationNo"
        class="application-card"
    >
      <div class="application-main">
        <!-- 이미지 섹션 -->
        <div class="application-image">
          <img :src="`http://localhost:9876/images/${application.campaign.imageUrl}`" alt="캠페인 이미지" />
        </div>

        <!-- 정보 섹션 -->
        <div class="application-info" :class="{ rejected: application.status === 'REJECTED' }">
          <h3 class="campaign-title">{{ application.campaign.title }}</h3>
          <p><strong>신청 코멘트:</strong> {{ application.comment }}</p>
          <p><strong>상태:</strong> {{ statusText[application.status] }}</p>
          <p><strong>신청일:</strong> {{ formatDate(application.createdAt) }}</p>
        </div>

        <!-- 버튼 영역 -->
        <div class="application-manage">
          <!-- 신청 취소 버튼 -->
          <button
              class="manage-button"
              v-if="application.status !== 'APPROVED'"
              @click="deleteApplication(application.applicationNo)"
          >
            신청 취소
          </button>

          <!-- 리뷰 작성 버튼 -->
          <router-link
              class="manage-button review"
              v-if="application.status === 'APPROVED'"
              to="/review/form"
          >
            리뷰 작성하기
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'
import { getUserFromToken } from '@/utils/jwt'
import { getUserNo } from '@/utils/jwt'

// const userNo = 1 // 🔹 임시: 실제 로그인된 유저 ID로 대체 필요
const applications = ref([]) // 모든 신청 목록
const filter = ref('ALL') // 필터 상태
const statusText = {
  PENDING: '처리 중',
  APPROVED: '선정됨',
  REJECTED: '미선정'
}

//jwt 토큰으로 불러오는 방식
// const userInfo = getUserFromToken()
// if (!userInfo || !userInfo.userNo) {
//   alert('로그인이 필요합니다.')
//   // router.push('/login')
// }

//localstorage로 불러오는 방식
const userInfo = getUserNo()
if(!userInfo) {
  alert('로그인이 필요합니다.')
  router.push('/login')
}

const userNo = userInfo.userNo

const fetchApplications = async () => {
  try {
    const { data } = await axios.get(`/api/application/myapplication/${userNo}`)
    applications.value = data
  } catch (error) {
    console.error('신청 목록 불러오기 오류:', error)
  }
}

const deleteApplication = async (applicationNo) => {
  if (confirm('정말 취소하시겠습니까?')) {
    try {
      await axios.delete(`/api/application/myapplication/delete/${applicationNo}`)
      applications.value = applications.value.filter(a => a.applicationNo !== applicationNo)
    } catch (error) {
      console.error('취소 실패:', error)
      alert('취소 중 오류가 발생했습니다.')
    }
  }
}

const formatDate = (dateString) => {
  const date = new Date(dateString)
  return date.toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit'
  })
}

// 상태별로 필터링된 신청 목록
const filteredApplications = computed(() => {
  if (filter.value === 'ALL') {
    return applications.value
  } else {
    return applications.value.filter(application => application.status === filter.value)
  }
})

const filterApplications = (status) => {
  filter.value = status
}

onMounted(() => {
  fetchApplications()
})
</script>

<style scoped>
.my-application-wrapper {
  max-width: 1000px;
  margin: 40px auto;
  padding: 20px;
  font-family: 'Noto Sans KR', sans-serif;
}

.page-title {
  font-size: 1.8rem;
  font-weight: bold;
  text-align: center;
  margin-bottom: 32px;
  color: #2c3e50;
}

.status-filter {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 24px;
}

.status-filter button {
  padding: 10px 16px;
  font-size: 1rem;
  cursor: pointer;
  border: 1px solid #ccc;
  border-radius: 6px;
  background-color: #fff;
  transition: background-color 0.3s ease;
}

.status-filter button.active {
  background-color: #3498db;
  color: white;
}

.empty-message {
  text-align: center;
  color: #999;
  font-size: 1rem;
  margin-top: 60px;
}

.application-card {
  background: #fff;
  border: 1px solid #e0e0e0;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 24px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.application-main {
  display: flex;
  gap: 24px;
  align-items: flex-start;
}

.application-image {
  width: 200px;
  height: 200px;
  border-radius: 10px;
  overflow: hidden;
  flex-shrink: 0;
}

.application-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
}

.application-info {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.application-info.rejected .campaign-title {
  color: #aaa;
}

.application-info.rejected p {
  color: #999;
}

.campaign-title {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 12px;
  color: #2c3e50;
}

.application-info p {
  font-size: 0.95rem;
  margin-bottom: 8px;
  color: #333;
}

.application-manage {
  margin-top: 16px;
}

.manage-button {
  background-color: #e74c3c;
  color: white;
  padding: 10px 16px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 0.9rem;
  text-decoration: none;
  display: inline-block;
  transition: background-color 0.2s ease;
}

.manage-button:hover {
  background-color: #c0392b;
}

.manage-button.review {
  background-color: #3498db;
}

.manage-button.review:hover {
  background-color: #2980b9;
}
</style>
