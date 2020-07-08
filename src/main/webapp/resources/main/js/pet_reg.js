$(function() {	
    $("#re")
            .on(
                    "click",
                    function() {
                        if ($("#name").val() == "") {
                            alert("이름을 적어주세요");
                            return false;
                        } else if ($("#upload").val() == "") {
                            alert("이미지 파일을 첨부해주세요");
                            return false;
                        } else if ($('input[name="pet_gender"]:checked')
                                .val() == undefined) {
                            alert("성별을 선택해 주세요");
                            return false;
                        } else if ($('input[name="pet_ox"]:checked')
                                .val() == undefined) {
                            alert("중성화여부를 선택해 주세요");
                            return false;
                        } else if ($("#petage").val() == "") {
                            alert("펫 나이를 적어주세요");
                            return false;
                        } else if ($("#type").val() == "") {
                            alert("견종을 적어주세요");
                            return false;
                        } else if ($('input[name="pet_family"]:checked')
                                .val() == undefined) {
                            alert("동거여부를 선택해 주세요");
                            return false;
                        }else if ($('input[name="pet_animal"]:checked')
                                .val() == undefined) {
                            alert("개인지 고양이 인지 선택해주세요");
                            return false;
                        }
                        var pettype = new Array("견종", "골든리트리버",
                                "골든두들", "그레이하운드", "그레이트데인",
                                "그레이트피레니즈", "그린란드견", "기슈견", "까나리오",
                                "닥스훈트", "달마시안", "도고아르헨티노", "도베르만",
                                "도사견", "동경이", "라사압소", "라페이로도알렌테조",
                                "래브라도리트리버", "레온베르거", "로트와일러",
                                "리트리버", "마스티프", "맨체스터테리어", "몰티즈",
                                "미니어처불테리어", "사모예드", "사플라니낙", "삽살개",
                                "샤페이", "세인트버나드", "세터", "셔틀랜드십독",
                                "솔로이츠쿠인틀레", "스코티쉬테리어", "스키퍼키",
                                "스태퍼드셔불테리어", "스피츠", "시바견",
                                "시베리안허스키", "시추", "시코쿠견", "실키테리어",
                                "빠삐용", "바셋하운드", "버니즈마운틴 독",
                                "베들링턴테리어", "벨지언쉽독", "보더콜리",
                                "보르도마스티프", "보르조이", "보비에드플랜더스",
                                "보스턴테리어", "복서", "불개", "불도그",
                                "불리쿠타", "불마스티프", "불테리어", "브리어드",
                                "비글", "비숑프리제", "마스티프", "맨체스터테리어",
                                "몰티즈", "미니어처불테리어", "미니어처슈나우저",
                                "미니어처핀셔", "아메리칸불리", "아메리칸스태퍼드셔테리어",
                                "아이디", "아이리시울프하운드", "아키타견", "아펜핀셔",
                                "아프간하운드", "알래스칸말라뮤트", "알래스칸클리카이",
                                "에스트렐라마운틴독", "오브차카", "올드잉글리시십독",
                                "요크셔테리어", "웨스트 하이랜드화이트테리어", "웰시코기",
                                "재패니즈스피츠", "재패니즈친", "잭러셀테리어",
                                "저먼셰퍼드", "제주개", "진돗개", "차우차우",
                                "치와와", "카네코르소", "카발리에킹찰스스파니엘",
                                "캉갈", "코리안마스티프", "코몬도르", "코커스파니엘",
                                "코통드튈레아르", "콜리", "테리어", "티베탄마스티프",
                                "done");
                        for (var i = 0; i < pettype.length; i++) {
                            if (pettype[i] == $("#type").val()) {
                                break;
                            } else if (pettype[i] == "done") {
                                alert("견종을 다시 한번 확인해주세요")
                                return false;
                            }

                        }
                        var regex= /^[0-9]+$/g;
                        var petage = $("#petage").val();
                        var what = regex.test(petage);
                        if(what == false || petage<0 || petage>30){
                            alert("펫나이를 확인해주세요");
                            return false;
                        }
                        alert("등록에 성공하셨습니다");
                    })
})
