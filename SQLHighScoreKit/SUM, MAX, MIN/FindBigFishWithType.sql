-- 낚시앱에서 사용하는 FISH_INFO 테이블은 잡은 물고기들의 정보를 담고 있습니다. FISH_INFO 테이블의 구조는 다음과 같으며 ID, FISH_TYPE, LENGTH, TIME은 각각 잡은 물고기의 ID, 물고기의 종류(숫자), 잡은 물고기의 길이(cm), 물고기를 잡은 날짜를 나타냅니다.
--
-- Column name	Type	Nullable
-- ID	INTEGER	FALSE
-- FISH_TYPE	INTEGER	FALSE
-- LENGTH	FLOAT	TRUE
-- TIME	DATE	FALSE
-- 단, 잡은 물고기의 길이가 10cm 이하일 경우에는 LENGTH 가 NULL 이며, LENGTH 에 NULL 만 있는 경우는 없습니다.
--
-- FISH_NAME_INFO 테이블은 물고기의 이름에 대한 정보를 담고 있습니다. FISH_NAME_INFO 테이블의 구조는 다음과 같으며, FISH_TYPE, FISH_NAME 은 각각 물고기의 종류(숫자), 물고기의 이름(문자) 입니다.
--
-- Column name	Type	Nullable
-- FISH_TYPE	INTEGER	FALSE
-- FISH_NAME	VARCHAR	FALSE
-- 문제
-- 물고기 종류 별로 가장 큰 물고기의 ID, 물고기 이름, 길이를 출력하는 SQL 문을 작성해주세요.
--
-- 물고기의 ID 컬럼명은 ID, 이름 컬럼명은 FISH_NAME, 길이 컬럼명은 LENGTH로 해주세요.
-- 결과는 물고기의 ID에 대해 오름차순 정렬해주세요.
-- 단, 물고기 종류별 가장 큰 물고기는 1마리만 있으며 10cm 이하의 물고기가 가장 큰 경우는 없습니다.
--
-- 예시
-- 예를 들어 FISH_INFO 테이블이 다음과 같고
--
-- ID	FISH_TYPE	LENGTH	TIME
-- 0	0	30	2021/12/04
-- 1	0	50	2020/03/07
-- 2	0	40	2020/03/07
-- 3	1	20	2022/03/09
-- 4	1	NULL	2022/04/08
-- 5	2	13	2021/04/28
-- 6	0	60	2021/07/27
-- 7	0	55	2021/01/18
-- 8	2	73	2020/01/28
-- 9	1	73	2021/04/08
-- 10	2	22	2020/06/28
-- 11	2	17	2022/12/23
-- FISH_NAME_INFO 테이블이 다음과 같다면
--
-- FISH_TYPE	FISH_NAME
-- 0	BASS
-- 1	SNAPPER
-- 2	ANCHOVY
-- 'BASS' 중 가장 큰 물고기는 60cm 로 물고기 ID 가 6이고, 'SNAPPER' 중 가장 큰 물고기는 73cm 로 물고기 ID가 9입니다. 'ANCHOVY' 중 가장 큰 물고기는 73cm 로 물고기 ID가 8입니다. 따라서 물고기 ID(ID) 에 대해 오름차순 정렬한다면 결과는 다음과 같습니다.
--
-- ID	FISH_NAME	LENGTH
-- 6	BASS	60
-- 8	ANCHOVY	73
-- 9	SNAPPER	73
--
-- ========================================================================================
--
SELECT F.ID, N.FISH_NAME, F.LENGTH
FROM FISH_INFO F
         JOIN FISH_NAME_INFO N ON F.FISH_TYPE = N.FISH_TYPE
         JOIN(SELECT FISH_TYPE, MAX(LENGTH) AS LENGTH
              FROM FISH_INFO
              GROUP BY FISH_TYPE) MAX_FISH ON F.FISH_TYPE = MAX_FISH.FISH_TYPE AND F.LENGTH = MAX_FISH.LENGTH
ORDER BY F.ID;
--
--
--
-- FISH_INFO AS F, FISH_NAME_INFO AS N으로
-- FISH_TYPE으로 네츄럴 조인 한 뒤
-- 서브쿼리로 각 FISH_TYPE 그룹으로 묶어 MAX(LENGTH)를 조회 한 뒤 임시 테이블로 만들고
-- 앞의 네츄럴 조인 한 테이블과 FISH_TYPE으로 임시 테이블과 조인 후 F.ID로 정렬