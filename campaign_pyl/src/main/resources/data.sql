INSERT INTO CATEGORY (name, description) VALUES
('음식', '다양한 식음료 체험'),
('미용', '화장품 및 스킨케어 제품 체험'),
('제품', '전자기기 및 기타 제품 체험');

INSERT INTO CAMPAIGN (
    title, description, start_date, end_date,
    current_count, recruit_count, image_url,
    status, progress_status, created_at, category_no
) VALUES
-- 음식 카테고리 (1)
('프리미엄 즉석 떡볶이 체험단', '매콤달콤한 즉석 떡볶이 제품을 직접 체험해보세요!',
 '2025-05-01T00:00:00', '2025-05-10T23:59:59',
 5, 30, 'sanju-pandita-HzmeZP0rMRw-unsplash.jpg',
 'APPROVED', 'WAITING', '2025-04-21T09:00:00', 1),

('비건 도시락 리뷰 캠페인', '채식 도시락을 체험하고 솔직한 리뷰를 남겨주세요.',
 '2025-05-03T00:00:00', '2025-05-12T23:59:59',
 10, 20, 'ella-olsson-lMcRyBx4G50-unsplash.jpg',
 'PENDING', 'WAITING', '2025-04-20T14:30:00', 1),

-- 미용 카테고리 (2)
('촉촉보습 수분크림 체험단', '건성 피부에 딱 맞는 수분크림을 체험해보세요.',
 '2025-05-05T00:00:00', '2025-05-15T23:59:59',
 8, 25, 'poko-skincare-QTGB4D8kDoc-unsplash.jpg',
 'APPROVED', 'ONGOING', '2025-04-19T13:00:00', 2),

('립틴트 신제품 런칭 체험단', '올봄 신상 립틴트를 누구보다 먼저 체험해보세요!',
 '2025-05-08T00:00:00', '2025-05-18T23:59:59',
 3, 50, 'kornchanok-chanwaro-BAZbIKSxAQU-unsplash.jpg',
 'APPROVED', 'WAITING', '2025-04-18T10:45:00', 2),

('진정 앰플 체험 캠페인', '민감 피부를 위한 저자극 앰플을 경험해보세요.',
 '2025-05-10T00:00:00', '2025-05-20T23:59:59',
 0, 40, 'the-design-lady-SrkxclLL4ps-unsplash.jpg',
 'REJECTED', 'WAITING', '2025-04-17T17:15:00', 2),

-- 제품 카테고리 (3)
('스마트 무선 이어폰 체험단', '고음질 무선 이어폰을 직접 사용해보고 리뷰해보세요!',
 '2025-05-02T00:00:00', '2025-05-11T23:59:59',
 15, 30, 'aaina-sharma-rI2MXeP6sss-unsplash.jpg',
 'APPROVED', 'ONGOING', '2025-04-16T16:00:00', 3),

('블루투스 스피커 체험단 모집', '감각적인 디자인의 블루투스 스피커 리뷰 캠페인!',
 '2025-05-06T00:00:00', '2025-05-13T23:59:59',
 6, 15, 'habib-dadkhah-S0B-pmGjdVA-unsplash.jpg',
 'APPROVED', 'WAITING', '2025-04-15T11:11:00', 3),

('포켓빔 프로젝터 체험단', '작고 가벼운 고화질 빔프로젝터 사용기 작성 캠페인!',
 '2025-05-07T00:00:00', '2025-05-17T23:59:59',
 2, 10, 'jean-luc-picard-4i7wMZwLhU8-unsplash.jpg',
 'PENDING', 'REVIEWING', '2025-04-14T13:30:00', 3),

('스마트 체중계 리뷰단 모집', '건강관리 필수템, 체중계 제품을 무료로 체험해보세요.',
 '2025-05-09T00:00:00', '2025-05-19T23:59:59',
 4, 20, 'i-yunmai-5jctAMjz21A-unsplash.jpg',
 'APPROVED', 'WAITING', '2025-04-13T09:40:00', 3),

('휴대용 손선풍기 체험단', '여름철 필수 아이템 손선풍기를 체험하고 리뷰 작성!',
 '2025-05-04T00:00:00', '2025-05-14T23:59:59',
 7, 25, 'chandan-chaurasia-ZBa8Jp411kY-unsplash.jpg',
 'APPROVED', 'COMPLETED', '2025-04-12T15:00:00', 3);

-- Users 샘플 데이터
INSERT INTO users (id, password, nickname, email, post_url, phone, role, created_at)
VALUES
    ('user001', 'pass1234', '초코맛쿠키', 'cookie1@example.com', 'https://instagram.com/cookie1', '010-1111-2222', 'USER', '2025-04-21 10:30:00'),

    ('user002', 'secret5678', '민초단장', 'mintcho@example.com', 'https://instagram.com/mintcho', '010-2222-3333', 'USER', '2025-04-20 09:00:00'),

    ('admin01', 'adminpass', '관리자', 'admin@example.com', 'https://instagram.com/admin', '010-9999-0000', 'ADMIN', '2025-04-18 08:00:00');

INSERT INTO application (
    campaign_no, user_no, comment, status, created_at
) VALUES
-- 프리미엄 즉석 떡볶이 체험단
(1, 1, '매콤한 떡볶이 리뷰 자신 있어요!', 'PENDING', '2025-04-21T11:00:00'),

-- 비건 도시락 리뷰 캠페인
(2, 1, '채식 도시락이라니! 꼭 해보고 싶어요.', 'APPROVED', '2025-04-21T11:30:00'),

-- 촉촉보습 수분크림 체험단
(3, 1, '건성 피부에 딱이에요. 후기 자신 있습니다.', 'PENDING', '2025-04-21T12:00:00'),

-- 스마트 무선 이어폰 체험단
(6, 1, '출퇴근길에 매일 써보고 자세히 써볼게요.', 'APPROVED', '2025-04-21T12:15:00'),

-- 휴대용 손선풍기 체험단
(10, 1, '여름철 필수템! 사용기 잘 남길게요.', 'REJECTED', '2025-04-21T12:30:00');
