#include<bits/stdc++.h>

#define ll long long
using namespace std;

void solve(){
    long long n ;
    cin>>n;
    vector<long long>arr(n);
    for(int i = 0; i < n; i++) cin>>arr[i];
     map<long long,long long>mp;
     for(int i = 0; i < n; i++) mp[arr[i]] = i;
    
}

int main(){
    int t;
    cin>>t;
    while(t--){
        solve();
    }
    return 0;
}