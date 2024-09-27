from fastapi import FastAPI, Form
from fastapi.responses import HTMLResponse
from fastapi.templating import Jinja2Templates
from starlette.requests import Request

# 假设有一个 chat_comp 模块，这里仅为示例
# from your_chat_module import chat_comp

import os
import qianfan
os.environ["QIANFAN_ACCESS_KEY"] = "b6a1c2504ae34ba5bd166f692a2107f1"
os.environ["QIANFAN_SECRET_KEY"] = "f41322ec4ac249a6b1fd26a8d1730cc9"
chat_completion = qianfan.ChatCompletion(ak="tytOvabDaLga28f3AQyZZKxz", sk="vXTK8mrMibZZJ5beFime4di33HmOunTt")

chat_comp = qianfan.ChatCompletion()

app = FastAPI()

templates = Jinja2Templates(directory="templates")

@app.get("/", response_class=HTMLResponse)
async def read_item(request: Request):
    return templates.TemplateResponse("index.html", {"request": request, "answer": ""})

@app.post("/submit/")
async def submit_item(request: Request, user_input: str = Form(...)):
    # 模拟响应，替换为实际调用 chat_comp.do
    resp = chat_comp.do(model="ERNIE-4.0-8K", messages=[{"role": "user", "content": user_input}])
    if resp:
        answer = resp["body"]["result"]
    # answer = f"模拟响应: {user_input}"  # 这里仅为示例，替换为实际响应
        return templates.TemplateResponse("index.html", {"request": request, "answer": answer})

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)
