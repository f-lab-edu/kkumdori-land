Feature: 유저 관련 기능
  Background:
    Given 유저 정보를 생성하고
      | name | email | password |
      | test1 | test1@admin.com | 1234 |
      | test2 | test2@admin.com | 1234 |
      | test3 | test3@admin.com | 1234 |

    Scenario: 유저는 회원가입한다.
      When 유저는 "test4", "test4@admin.com", "1234"을 이용해 회원가입한다.
      Then 유저는 회원가입에 성공한다.

    Scenario: 유저는 회원가입에 실패한다.
      When 유저는 "test1", "test1@admin.com", "1234"을 이용해 회원가입한다.
      Then 유저는 회원가입에 409으로 실패한다.
