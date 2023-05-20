class TokenService {
    getLocalRefreshToken() {
        const tokenInfo = JSON.parse(localStorage.getItem("tokenInfo"));
        return tokenInfo?.refresh_token;
    }
    getLocalIdToken() {
        const tokenInfo = JSON.parse(localStorage.getItem("tokenInfo"));
        return tokenInfo?.id_token;
    }
    getTokenInfo() {
        return JSON.parse(localStorage.getItem("tokenInfo"));
    }
    setTokenInfo(tokenInfo) {
        localStorage.setItem("tokenInfo", JSON.stringify(tokenInfo));
    }
    setUser(user) {
        localStorage.setItem("user", JSON.stringify(user));
    }
    removeUser() {
        localStorage.removeItem("user");
        localStorage.removeItem("tokenInfo");
    }
}
export default new TokenService();