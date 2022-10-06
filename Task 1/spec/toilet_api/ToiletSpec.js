const axios = require('axios');
var url;
var username;
var password;

beforeAll(async function() { 
  const response = await axios.get('http://ptsv2.com/t/fu807-1554722621/post');
  username = response.data.username;
  password = response.data.password;
  url = response.data.targetUrl;
});  

describe('Toilet API Suite', function() {
  it('API call should return 401 code when no username and password provided', async function() {
    await axios.post(url, {}, {
      auth: {
        username: '',
        password: ''
      }
    }).catch(function(error) {
      expect(error.response.status).toEqual(401);
      console.log("Status code is 401 when crediantials are incorrect Verification Passed")
    });
  });

  it('API call should return 200 code when username and password are provided', async function() {
    const response = await axios.post(url, {}, {
      auth: {
        username: username,
        password: password
      }
    }).catch(function(error) {
      console.log(error);
    });
    expect(response.status).toEqual(200);
    console.log("Status code is 200 when crediantials are correct Verification Passed")
  });

  it('API call returns an IP address which starts with 192.', async function() {
    const response = await axios.post(url, {}, {
      auth: {
        username: username,
        password: password
      }
    }).catch(function(error) {
      console.log(error);
    });
    expect(response.data.ip).toContain('192.')
    console.log("The Call returned a valid IP address that starts with 192. Verification Passed")
  });

  it('API call returned token that has 10 symbols', async function() {
    const response = await axios.post(url, {}, {
      auth: {
        username: username,
        password: password
      }
    }).catch(function(error) {
      console.log(error);
    });
    expect(response.data.token.length).toEqual(10);
    console.log("The Call returned token with 10 symbols verification passed")
  });
});