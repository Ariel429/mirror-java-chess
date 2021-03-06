# java-chess
체스 게임 구현을 위한 저장소

## 기능 요구 사항

### 1단계
- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### 2단계
- 체스 말의 이동 규칙을 찾아보고 체스 말이 이동할 수 있도록 구현한다.
- move source위치 target위치을 실행해 이동한다.
- 체스판의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

### 3단계
- 체스 게임은 상대편 King이 잡히는 경우 게임에서 진다. King이 잡혔을 때 게임을 종료해야 한다.
- 체스 게임은 현재 남아 있는 말에 대한 점수를 구할 수 있어야 한다.
- "status" 명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 볼 수 있어야 한다.

#### 점수 계산 규칙
- 체스 프로그램에서 현재까지 남아 있는 말에 따라 점수를 계산할 수 있어야 한다.
- 각 말의 점수는 queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
- pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
- king은 잡히는 경우 경기가 끝나기 때문에 점수가 없다.
- 한 번에 한 쪽의 점수만을 계산해야 한다.


--- 
### Coordinate란 좌표에서 x(File)나 y(Rank) 같은 성분을 의미한다

- [x] Coordinate는 자신의 값에 대해 알아야 한다.
- [x] Coordinate는 자신의 범위에 대해 알고, 체크해야 한다.

### Position이란 체스 판에서 한 점을 의미하고, File과 Rank로 구성된다.
- [x] Position은 자신의 file, rank 알아야 한다.
- [x] Position은 위치 객체를 미리 생성해서 캐싱해야 한다.
- [x] Position은 입력 값에 맞는 Position 객체를 반환해야 한다.(입력값은 String)
- [ ] Position은 다른 Position을 받아서 상대 위치를 계산할 수 있어야 한다.

### Piece란 체스 말을 의미한다
- [x] Piece는 자신의 위치를 알아야 한다.
- [x] Piece는 자신이 속한 Player가 누구인지 알아야 한다.
- [ ] Piece는 자신의 이동 규칙을 판단할 수 있어야 한다.
- [ ] Piece는 입력받은 위치에 이동 가능한지를 알아야 한다.
- [ ] Piece는 입력받은 위치에 이동가능하다면 이동할 수 있어야 한다.

#### Piece 종류
- Pawn
  - 처음 이동시에는 2칸 움직일 수 있다.
  - 다음부터는 1칸씩 움질일 수 있다.
  - 공격시에는 대각선만 이동 가능하다.
- Knight
  - 상하좌우로 1칸 움직이고 대각선 방향으로 1칸 이동 가능하다
  - 이동 경로에 장애물이 있어도 뛰어 넘을 수 있다
- Bishop
  - 대각선으로 무한정 이동 가능하다
- Rook
  - 상하좌우로 무한정 이동 가능하다
- Queen
  - 대각선, 상하좌우로 무한정 이동 가능하다
- King
  - 대각선, 상하좌우로 1칸 이동 가능하다

### Board란 체스 판을 의미한다
- [x] Board는 Position과 Piece를 Map으로 가지고 있다.
- [ ] Board는 source position과 target position을 받아 해당하는 말의 이동을 수행할 수 있다.

TODO
- Board에서 이동 후에 연결이 끊어진 Piece가 Position 정보를 가지고 있을텐데, 더 이상 Board에서 접근할 수 없기 때문에 상관 없나?

