<template>
  <div class="campaign-detail-container">
    <div class="campaign-main">
      <!-- 이미지 섹션 -->
      <div
          class="campaign-image"
          :class="{ blurred: isCompleted }"
      >
        <img :src="`http://localhost:9876/images/${campaign.imageUrl}`" alt="캠페인 이미지" />
      </div>

      <!-- 정보 섹션 -->
      <div class="campaign-info">
        <h2 class="campaign-title">[{{ campaign.category.name }}] {{ campaign.title }}</h2>
        <p class="campaign-description">{{ campaign.description }}</p>

        <!-- 모집 정보 -->
        <ul
            class="campaign-meta"
            :class="{ 'greyed-out': isReviewing || isCompleted }"
        >
          <li v-if="!isCompleted">
            <b>모집기간:</b> {{ formatDate(campaign.startDate) }} ~ {{ formatDate(campaign.endDate) }}
          </li>
          <li v-if="!isReviewing && !isCompleted">
            <b>모집인원:</b> {{ campaign.recruitCount }}명
          </li>
          <li v-if="!isReviewing && !isCompleted">
            <b>신청인원:</b> {{ campaign.currentCount }}명
          </li>
        </ul>

        <!-- 리뷰 기간 안내 -->
        <p v-if="isReviewing" class="reviewing-notice">
          🔍 현재 리뷰 작성 기간입니다 ({{ formatDate(reviewStartDate) }} ~ {{ formatDate(reviewEndDate) }})
        </p>

        <!-- 버튼 및 상태 문구 -->
        <div class="campaign-actions">
          <p v-if="isWaiting" class="state-notice">📌 현재 모집 대기중입니다.</p>
          <p v-if="isReviewing || isCompleted" class="state-notice">📌 모집 종료된 캠페인입니다.</p>
          <router-link :to="`/campaign/apply/${route.params.id}`">
            <button class="apply-button" :disabled="!isOngoing">신청하기</button>
          </router-link>
        </div>
      </div>
    </div>

    <!-- 공통 상세정보 영역 -->
    <div class="campaign-extra-info">
      <h3>❗ 유의사항</h3>
      <ul>
        <li>리뷰는 캠페인 종료 후 7일 이내에 작성해야 합니다.</li>
        <li>캠페인 내용과 무관한 리뷰는 삭제될 수 있습니다.</li>
        <li>선정 후 무통보 미참여 시 패널티가 부과됩니다.</li>
        <li>신청 시 기재한 정보가 사실과 다를 경우 선정에서 제외될 수 있습니다.</li>
      </ul>

      <h3>📢 기타 안내</h3>
      <ul>
        <li>신청 정보는 마이페이지에서 확인할 수 있습니다.</li>
        <li>리뷰어 선정은 모집 종료 후 1~2일 내에 개별 연락드립니다.</li>
        <li>본 캠페인은 내부 사정에 따라 조기 종료될 수 있습니다.</li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const campaign = ref({ category: {} })

const reviewStartDate = ref(null)
const reviewEndDate = ref(null)

const isWaiting = computed(() => campaign.value.progressStatus === 'WAITING')
const isOngoing = computed(() => campaign.value.progressStatus === 'ONGOING')
const isReviewing = computed(() => campaign.value.progressStatus === 'REVIEWING')
const isCompleted = computed(() => campaign.value.progressStatus === 'COMPLETED')

onMounted(async () => {
  const { data } = await axios.get(`/api/campaign/detail/${route.params.id}`)
  campaign.value = data

  if (isReviewing.value) {
    const endDate = new Date(campaign.value.endDate)
    reviewStartDate.value = endDate
    reviewEndDate.value = new Date(endDate)
    reviewEndDate.value.setDate(reviewEndDate.value.getDate() + 7)
  }
})

const formatDate = (dateStr) => {
  const d = new Date(dateStr)
  return `${d.getFullYear()}.${String(d.getMonth() + 1).padStart(2, '0')}.${String(d.getDate()).padStart(2, '0')}`
}
</script>

<style scoped>
.campaign-detail-container {
  padding: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.campaign-main {
  display: flex;
  gap: 40px;
  max-width: 1000px;
  width: 100%;
  border: 1px solid #e0e0e0;
  border-radius: 12px;
  padding: 20px;
  background: #fff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  margin-bottom: 40px;
}

.campaign-image {
  width: 400px;
  height: 400px; /* 정사각형 고정 */
  border-radius: 8px;
  overflow: hidden;
  flex-shrink: 0;
}

.campaign-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
}

.campaign-image.blurred img {
  filter: blur(6px);
  opacity: 0.6;
}

.campaign-info {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.campaign-actions {
  margin-top: auto;
}

.campaign-title {
  font-size: 1.8rem;
  font-weight: bold;
  margin-bottom: 12px;
}

.campaign-description {
  font-size: 1rem;
  margin-bottom: 20px;
  color: #333;
}

.campaign-meta {
  list-style: none;
  padding: 0;
  margin: 0 0 20px;
}

.campaign-meta li {
  margin-bottom: 10px;
  font-size: 0.95rem;
  color: #444;
}

.campaign-meta.greyed-out li {
  color: #aaa;
}

.state-notice {
  font-size: 1rem;
  color: #cc0000;
  font-weight: 600;
  margin-bottom: 20px;
}

.reviewing-notice {
  font-size: 1rem;
  color: #cc0000;
  margin-bottom: 20px;
  font-weight: 600;
}

.apply-button {
  background-color: #2cb148;
  color: white;
  font-weight: bold;
  border: none;
  padding: 12px 24px;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.2s ease;
  width: 100%;
}

.apply-button:disabled {
  background-color: #a5a5a5;
  cursor: not-allowed;
}

.apply-button:hover:not(:disabled) {
  background-color: #23953a;
}

.campaign-extra-info {
  max-width: 1000px;
  width: 100%;
  background: #f9f9f9;
  padding: 30px 40px;
  border-radius: 12px;
  font-size: 0.95rem;
  color: #333;

}

.campaign-extra-info h3 {
  margin-top: 0;
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 12px;
}

.campaign-extra-info ul {
  list-style: disc;
  padding-left: 20px;
  margin-bottom: 24px;
}

.campaign-extra-info li {
  margin-bottom: 8px;
}
</style>
